import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tienda {

    private String nombre;
    private String ubicacion;
    private ArbolProductos inventario;
    private ColaClientes colaClientes;
    private Grafo mapaUbicaciones;

    public Tienda(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
        this.mapaUbicaciones = new Grafo();

        mapaUbicaciones.agregarVertice(ubicacion);
        cargarMapaBase();
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    public Grafo getMapaUbicaciones() {
        return mapaUbicaciones;
    }

    public boolean agregarProductoAlInventario(String codigo, String nombre, int stock, double precio,
                                               String categoria, LocalDate fechaDeVencimiento) {

        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setCantidad(0);
        producto.setStock(stock);
        producto.setPrecio(precio);
        producto.setCategoria(categoria);
        producto.setFechaDeVencimiento(fechaDeVencimiento);

        return inventario.insertar(producto);
    }

    public boolean registrarCliente(int id, String nombre, byte prioridad, String ubicacionCliente) {
        if (colaClientes.buscarClientePorId(id) != null) {
            return false;
        }

        if (ubicacionCliente == null || ubicacionCliente.trim().isEmpty()) {
            return false;
        }

        ubicacionCliente = ubicacionCliente.trim();

        Cliente cliente = new Cliente(id, nombre, prioridad, ubicacionCliente);
        colaClientes.encolar(cliente);

        // La ubicación del cliente se agrega automáticamente como vértice al grafo
        mapaUbicaciones.agregarVertice(ubicacionCliente);

        return true;
    }

    public boolean agregarUbicacion(String ubicacion) {
        if (ubicacion == null || ubicacion.trim().isEmpty()) return false;
        mapaUbicaciones.agregarVertice(ubicacion.trim());
        return true;
    }

    public boolean agregarConexion(String origen, String destino, int distancia) {
        if (origen == null || destino == null) return false;
        if (origen.trim().isEmpty() || destino.trim().isEmpty()) return false;
        if (distancia <= 0) return false;

        mapaUbicaciones.agregarArista(origen.trim(), destino.trim(), distancia);
        return true;
    }

    public boolean agregarProductoAlCarrito(int idCliente, String nombreProducto, int cantidadDeseada) {
        if (cantidadDeseada <= 0) return false;

        Cliente cliente = colaClientes.buscarClientePorId(idCliente);
        if (cliente == null) return false;

        Producto productoInventario = inventario.buscarPorNombre(nombreProducto);
        if (productoInventario == null) return false;

        if (productoInventario.getStock() < cantidadDeseada) return false;

        boolean agregado = cliente.getCarrito().agregarOAcumularProducto(productoInventario, cantidadDeseada);

        if (agregado) {
            productoInventario.setStock(productoInventario.getStock() - cantidadDeseada);
        }

        return agregado;
    }

    public Cliente atenderSiguienteCliente() {
        Cliente siguiente = colaClientes.verSiguiente();

        if (siguiente == null) {
            return null;
        }

        if (mapaUbicaciones.estaDesconectado(siguiente.getUbicacion())) {
            System.out.println("No se puede atender al cliente.");
            System.out.println("La ubicación '" + siguiente.getUbicacion() + "' está desconectada del grafo.");
            return null;
        }

        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        mapaUbicaciones.algoritmoDijkstra(ubicacion, distancias, predecesores);

        List<String> camino = mapaUbicaciones.reconstruirCamino(ubicacion, siguiente.getUbicacion(), predecesores);

        if (camino.isEmpty()) {
            System.out.println("No se puede atender al cliente.");
            System.out.println("No existe un camino entre la tienda y la ubicación del cliente.");
            return null;
        }

        return colaClientes.desencolar();
    }

    public void imprimirFactura(Cliente cliente) {
        if (cliente == null) {
            System.out.println("No hay cliente para facturar.");
            return;
        }

        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        mapaUbicaciones.algoritmoDijkstra(ubicacion, distancias, predecesores);

        List<String> camino = mapaUbicaciones.reconstruirCamino(ubicacion, cliente.getUbicacion(), predecesores);
        int distanciaTotal = distancias.getOrDefault(cliente.getUbicacion(), Integer.MAX_VALUE);

        System.out.println("=============== FACTURA ===============");
        System.out.println("Tienda: " + nombre);
        System.out.println("Ubicación tienda: " + ubicacion);
        System.out.println("Cliente: " + cliente.getNombreCliente());
        System.out.println("ID: " + cliente.getClienteID());
        System.out.println("Prioridad: " + cliente.getPrioridad() + " (" + cliente.getTipoPrioridad() + ")");
        System.out.println("Ubicación cliente: " + cliente.getUbicacion());
        System.out.println("---------------------------------------");
        cliente.getCarrito().imprimirFactura();

        System.out.println("---------------------------------------");
        if (camino.isEmpty() || distanciaTotal == Integer.MAX_VALUE) {
            System.out.println("No existe ruta disponible para la entrega.");
        } else {
            System.out.println("Camino más corto: " + String.join(" -> ", camino));
            System.out.println("Distancia total: " + distanciaTotal);
        }
        System.out.println("=======================================");
    }

    private void cargarMapaBase() {
        mapaUbicaciones.agregarVertice("Heredia");
        mapaUbicaciones.agregarVertice("Alajuela");
        mapaUbicaciones.agregarVertice("Cartago");
        mapaUbicaciones.agregarVertice("San Pedro");
        mapaUbicaciones.agregarVertice("Escazu");

        mapaUbicaciones.agregarArista(ubicacion, "Heredia", 8);
        mapaUbicaciones.agregarArista(ubicacion, "Cartago", 20);
        mapaUbicaciones.agregarArista("Heredia", "Alajuela", 10);
        mapaUbicaciones.agregarArista("Heredia", "San Pedro", 15);
        mapaUbicaciones.agregarArista("San Pedro", "Cartago", 12);
        mapaUbicaciones.agregarArista("Escazu", ubicacion, 7);
    }
}