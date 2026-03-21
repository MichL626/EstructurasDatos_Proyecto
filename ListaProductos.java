import java.time.LocalDate;
import java.util.Objects;

public class ListaProductos {

    private Producto primero;

    public ListaProductos() {
        this.primero = null;
    }

    public Producto getPrimero() {
        return primero;
    }

    public void setPrimero(Producto primero) {
        this.primero = primero;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    // =========================
    // INSERTAR PRODUCTO NUEVO
    // =========================
    public boolean insertarInicio(String codigo, String nombre, int cantidad, int stock, double precio,
                                  String categoria, LocalDate fechaDeVencimiento) {

        if (buscar(codigo) != null) return false;

        Producto nuevo = new Producto();
        nuevo.setCodigo(codigo);
        nuevo.setNombre(nombre);
        nuevo.setCantidad(cantidad);
        nuevo.setStock(stock);
        nuevo.setPrecio(precio);
        nuevo.setCategoria(categoria);
        nuevo.setFechaDeVencimiento(fechaDeVencimiento);

        nuevo.setSiguiente(primero);
        primero = nuevo;
        return true;
    }

    public boolean insertarFinal(String codigo, String nombre, int cantidad, int stock, double precio,
                                 String categoria, LocalDate fechaDeVencimiento) {

        if (buscar(codigo) != null) return false;

        Producto nuevo = new Producto();
        nuevo.setCodigo(codigo);
        nuevo.setNombre(nombre);
        nuevo.setCantidad(cantidad);
        nuevo.setStock(stock);
        nuevo.setPrecio(precio);
        nuevo.setCategoria(categoria);
        nuevo.setFechaDeVencimiento(fechaDeVencimiento);
        nuevo.setSiguiente(null);

        if (estaVacia()) {
            primero = nuevo;
            return true;
        }

        Producto aux = primero;
        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
        }

        aux.setSiguiente(nuevo);
        return true;
    }

    // INSERTAR DESDE OBJETO
    public boolean insertarProductoFinal(Producto producto) {
        if (producto == null || producto.getCodigo() == null) return false;
        if (buscar(producto.getCodigo()) != null) return false;

        Producto copia = copiarProducto(producto);
        copia.setSiguiente(null);

        if (estaVacia()) {
            primero = copia;
            return true;
        }

        Producto aux = primero;
        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
        }

        aux.setSiguiente(copia);
        return true;
    }

    // AGREGAR O SUMAR CANTIDAD
    // Ideal para carrito
    public boolean agregarOAcumularProducto(Producto producto, int cantidadAgregar) {
        if (producto == null || cantidadAgregar <= 0) return false;

        Producto existente = buscar(producto.getCodigo());

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidadAgregar);
            return true;
        }

        Producto nuevo = copiarProducto(producto);
        nuevo.setCantidad(cantidadAgregar);
        nuevo.setSiguiente(null);

        if (estaVacia()) {
            primero = nuevo;
            return true;
        }

        Producto aux = primero;
        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
        }

        aux.setSiguiente(nuevo);
        return true;
    }

    // BUSCAR
    public Producto buscar(String codigo) {
        if (estaVacia() || codigo == null) return null;

        Producto temp = primero;
        while (temp != null) {
            if (Objects.equals(temp.getCodigo(), codigo)) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    // MODIFICAR
    public boolean modificar(String codigo, String nombre, int cantidad, int stock, double precio,
                             String categoria, LocalDate fechaDeVencimiento) {

        Producto p = buscar(codigo);
        if (p == null) return false;

        p.setNombre(nombre);
        p.setCantidad(cantidad);
        p.setStock(stock);
        p.setPrecio(precio);
        p.setCategoria(categoria);
        p.setFechaDeVencimiento(fechaDeVencimiento);
        return true;
    }

    public boolean agregarImagenAProducto(String codigo, String ruta) {
        Producto p = buscar(codigo);
        if (p == null) return false;
        p.agregarImagen(ruta);
        return true;
    }

    // ELIMINAR
    public Producto eliminar(String codigo) {
        if (estaVacia() || codigo == null) return null;

        if (Objects.equals(primero.getCodigo(), codigo)) {
            Producto eliminado = primero;
            primero = primero.getSiguiente();
            eliminado.setSiguiente(null);
            return eliminado;
        }

        Producto anterior = primero;
        Producto actual = primero.getSiguiente();

        while (actual != null && !Objects.equals(actual.getCodigo(), codigo)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) return null;

        anterior.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(null);
        return actual;
    }

    // MOSTRAR
    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        Producto temp = primero;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getSiguiente();
        }
    }

    public void mostrarCarrito() {
        if (estaVacia()) {
            System.out.println("El carrito está vacío.");
            return;
        }

        System.out.println("============= CARRITO =============");
        Producto temp = primero;
        while (temp != null) {
            double subtotal = temp.getCantidad() * temp.getPrecio();

            System.out.println("Código: " + temp.getCodigo());
            System.out.println("Nombre: " + temp.getNombre());
            System.out.println("Cantidad: " + temp.getCantidad());
            System.out.println("Precio unitario: " + temp.getPrecio());
            System.out.println("Subtotal: " + subtotal);
            System.out.println("----------------------------------");

            temp = temp.getSiguiente();
        }
    }

    // CÁLCULOS
    public double calcularTotal() {
        double total = 0;
        Producto temp = primero;

        while (temp != null) {
            total += temp.getCantidad() * temp.getPrecio();
            temp = temp.getSiguiente();
        }

        return total;
    }

    public int contarProductos() {
        int contador = 0;
        Producto temp = primero;

        while (temp != null) {
            contador++;
            temp = temp.getSiguiente();
        }

        return contador;
    }

    // FACTURA / REPORTE
    public void reporteCostos() {
        if (estaVacia()) {
            System.out.println("La lista está vacía. No hay reporte.");
            return;
        }

        System.out.println("========== REPORTE DE COSTOS ==========");
        double totalAcumulado = 0;

        Producto temp = primero;
        while (temp != null) {
            double subtotal = temp.getPrecio() * temp.getCantidad();
            totalAcumulado += subtotal;

            System.out.println("Código: " + temp.getCodigo());
            System.out.println("Nombre: " + temp.getNombre());
            System.out.println("Cantidad: " + temp.getCantidad());
            System.out.println("Precio unitario: " + temp.getPrecio());
            System.out.println("Subtotal: " + subtotal);
            System.out.println("--------------------------------------");

            temp = temp.getSiguiente();
        }

        System.out.println("TOTAL ACUMULADO: " + totalAcumulado);
        System.out.println("======================================");
    }

    public void imprimirFactura() {
        if (estaVacia()) {
            System.out.println("No hay productos en el carrito.");
            return;
        }

        System.out.println("=============== FACTURA ===============");
        Producto temp = primero;

        while (temp != null) {
            double subtotal = temp.getCantidad() * temp.getPrecio();

            System.out.println(temp.getNombre()
                    + " | Cantidad: " + temp.getCantidad()
                    + " | Precio: " + temp.getPrecio()
                    + " | Subtotal: " + subtotal);

            temp = temp.getSiguiente();
        }

        System.out.println("---------------------------------------");
        System.out.println("TOTAL A PAGAR: " + calcularTotal());
        System.out.println("=======================================");
    }

    // APOYO INTERNO
    private Producto copiarProducto(Producto original) {
        Producto copia = new Producto();
        copia.setCodigo(original.getCodigo());
        copia.setNombre(original.getNombre());
        copia.setCantidad(original.getCantidad());
        copia.setStock(original.getStock());
        copia.setPrecio(original.getPrecio());
        copia.setCategoria(original.getCategoria());
        copia.setFechaDeVencimiento(original.getFechaDeVencimiento());

        if (original.getListaImagenes() != null) {
            for (String ruta : original.getListaImagenes()) {
                copia.agregarImagen(ruta);
            }
        }

        return copia;
    }
}