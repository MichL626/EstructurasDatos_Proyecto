public class NodoProducto {
    private Producto dato;
    private NodoProducto siguiente;

    public NodoProducto(Producto dato) {
        this.dato = dato;
        siguiente = null;
    }

    public Producto getDato() {return dato;}
    public void setDato(Producto dato) {this.dato = dato;}

    public NodoProducto getSiguiente() {return siguiente;}
    public void setSiguiente(NodoProducto siguiente) {this.siguiente = siguiente;}

    @Override
    public String toString() {
        return "NodoProducto{" +
                "dato=" + dato +
                ", siguiente=" + siguiente +
                '}';
    }

}
