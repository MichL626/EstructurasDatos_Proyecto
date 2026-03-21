import java.time.LocalDate;

public class Tienda {

    private String nombre;
    private ArbolProductos inventario;
    private ColaClientes colaClientes;

    public Tienda(String nombre) {
        this.nombre = nombre;
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
    }

    public String getNombre() {
        return nombre;
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
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

    public boolean registrarCliente(int id, String nombre, byte prioridad) {
        if (colaClientes.buscarClientePorId(id) != null) {
            return false;
        }

        Cliente cliente = new Cliente(id, nombre, prioridad);
        colaClientes.encolar(cliente);
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
        return colaClientes.desencolar();
    }

    public void imprimirFactura(Cliente cliente) {
        if (cliente == null) {
            System.out.println("No hay cliente para facturar.");
            return;
        }

        System.out.println("=============== FACTURA ===============");
        System.out.println("Tienda: " + nombre);
        System.out.println("Cliente: " + cliente.getNombreCliente());
        System.out.println("ID: " + cliente.getClienteID());
        System.out.println("Prioridad: " + cliente.getPrioridad() + " (" + cliente.getTipoPrioridad() + ")");
        System.out.println("---------------------------------------");
        cliente.getCarrito().imprimirFactura();
        System.out.println("=======================================");
    }
}