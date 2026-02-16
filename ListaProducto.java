public class ListaProducto {

    private NodoProducto primero;

    public ListaProducto() {
        this.primero = null;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public NodoProducto getPrimero() {
        return primero;
    }

    public void insertarInicio(Producto producto) {
        NodoProducto nodo = new NodoProducto(producto);
    }

    public void insertarFinal(Producto producto) {
        NodoProducto nodo = new NodoProducto(producto);

        if (estaVacia()) {
            primero = nodo;
            return;
        }

        NodoProducto temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nodo);
    }












}
