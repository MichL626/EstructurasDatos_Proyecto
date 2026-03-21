public class NodoProducto {
    public class NodoProducto {

        private Producto producto;
        private NodoProducto izquierdo;
        private NodoProducto derecho;

        public NodoProducto(Producto producto) {
            this.producto = producto;
            this.izquierdo = null;
            this.derecho = null;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public NodoProducto getIzquierdo() {
            return izquierdo;
        }

        public void setIzquierdo(NodoProducto izquierdo) {
            this.izquierdo = izquierdo;
        }

        public NodoProducto getDerecho() {
            return derecho;
        }

        public void setDerecho(NodoProducto derecho) {
            this.derecho = derecho;
        }
    }
}
