public class ArbolProductos {
    public class ArbolProductos {

        private NodoProducto raiz;

        public ArbolProductos() {
            this.raiz = null;
        }

        public NodoProducto getRaiz() {
            return raiz;
        }

        public boolean estaVacio() {
            return raiz == null;
        }

        public boolean insertar(Producto producto) {
            if (producto == null || producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
                return false;
            }

            if (buscarPorNombre(producto.getNombre()) != null) {
                return false; // no permitir nombres repetidos
            }

            raiz = insertarRec(raiz, producto);
            return true;
        }

        private NodoProducto insertarRec(NodoProducto actual, Producto producto) {
            if (actual == null) {
                return new NodoProducto(producto);
            }

            String nombreNuevo = producto.getNombre().trim().toLowerCase();
            String nombreActual = actual.getProducto().getNombre().trim().toLowerCase();

            if (nombreNuevo.compareTo(nombreActual) < 0) {
                actual.setIzquierdo(insertarRec(actual.getIzquierdo(), producto));
            } else if (nombreNuevo.compareTo(nombreActual) > 0) {
                actual.setDerecho(insertarRec(actual.getDerecho(), producto));
            }

            return actual;
        }

        public Producto buscarPorNombre(String nombre) {
            return buscarPorNombreRec(raiz, nombre);
        }

        private Producto buscarPorNombreRec(NodoProducto actual, String nombre) {
            if (actual == null || nombre == null) return null;

            String buscado = nombre.trim().toLowerCase();
            String actualNombre = actual.getProducto().getNombre().trim().toLowerCase();

            int cmp = buscado.compareTo(actualNombre);

            if (cmp == 0) return actual.getProducto();
            if (cmp < 0) return buscarPorNombreRec(actual.getIzquierdo(), nombre);
            return buscarPorNombreRec(actual.getDerecho(), nombre);
        }

        public void mostrarInOrden() {
            if (estaVacio()) {
                System.out.println("El inventario está vacío.");
                return;
            }

            System.out.println("========= INVENTARIO (EN ORDEN) =========");
            inOrdenRec(raiz);
            System.out.println("=========================================");
        }

        private void inOrdenRec(NodoProducto actual) {
            if (actual != null) {
                inOrdenRec(actual.getIzquierdo());
                System.out.println(actual.getProducto());
                inOrdenRec(actual.getDerecho());
            }
        }
    }
}
