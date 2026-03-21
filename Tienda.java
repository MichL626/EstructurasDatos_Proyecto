public class Tienda {
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

        public void setNombre(String nombre) {
            this.nombre = nombre;
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
            producto.setCantidad(0); // en inventario no representa compra aún
            producto.setStock(stock);
            producto.setPrecio(precio);
            producto.setCategoria(categoria);
            producto.setFechaDeVencimiento(fechaDeVencimiento);

            return inventario.insertar(producto);
        }

        public boolean registrarCliente(String id, String nombre, int prioridad) {
            try {
                Cliente cliente = new Cliente(id, nombre, prioridad);
                colaClientes.encolar(cliente);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        public Cliente buscarClienteEnCola(String id) {
            if (colaClientes.estaVacia() || id == null) return null;

            Cliente temp = colaClientes.getFrente();
            while (temp != null) {
                if (id.equalsIgnoreCase(temp.getId())) {
                    return temp;
                }
                temp = temp.getSiguiente();
            }
            return null;
        }

        public boolean agregarProductoAlCarrito(String idCliente, String nombreProducto, int cantidadDeseada) {
            if (cantidadDeseada <= 0) return false;

            Cliente cliente = buscarClienteEnCola(idCliente);
            if (cliente == null) return false;

            Producto productoInventario = inventario.buscarPorNombre(nombreProducto);
            if (productoInventario == null) return false;

            if (productoInventario.getStock() < cantidadDeseada) {
                return false;
            }

            Producto productoEnCarrito = cliente.getCarrito().buscar(productoInventario.getCodigo());

            if (productoEnCarrito != null) {
                productoEnCarrito.setCantidad(productoEnCarrito.getCantidad() + cantidadDeseada);
            } else {
                cliente.getCarrito().insertarFinal(
                        productoInventario.getCodigo(),
                        productoInventario.getNombre(),
                        cantidadDeseada,
                        productoInventario.getStock(),
                        productoInventario.getPrecio(),
                        productoInventario.getCategoria(),
                        productoInventario.getFechaDeVencimiento()
                );
            }

            productoInventario.setStock(productoInventario.getStock() - cantidadDeseada);
            return true;
        }

        public Cliente atenderSiguienteCliente() {
            return colaClientes.desencolar();
        }

        public void imprimirFactura(Cliente cliente) {
            if (cliente == null) {
                System.out.println("No hay cliente para facturar.");
                return;
            }

            System.out.println("============== FACTURA ==============");
            System.out.println("Tienda: " + nombre);
            System.out.println("Cliente: " + cliente.getNombre());
            System.out.println("ID: " + cliente.getId());
            System.out.println("Prioridad: " + cliente.getPrioridad() + " (" + cliente.getTipoPrioridad() + ")");
            System.out.println("-------------------------------------");
            cliente.getCarrito().reporteCostos();
            System.out.println("=====================================");
        }
    }
}
