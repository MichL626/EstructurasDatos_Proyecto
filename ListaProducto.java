import java.time.LocalDate;

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
// A Partir de aquí está lo de la clase

    // Atributos
    private Producto primero;

    // Métodos
    // Constructor

    public ListaProducto() {
        this.primero = null;
    }

    // Getters

    public Producto getPrimero() {
        return primero;
    }

    // Setters

    public void setPrimero(Producto primero) {
        this.primero = primero;
    }

    // Operaciones
    public void insertarProductoInicio(String nombre, String codigo, byte stock, double precio, String categoria, LocalDate fechaDeVencimiento, String imagen) {
        Producto productoInsertar = new Producto(codigo, nombre, stock, precio, categoria, fechaDeVencimiento, imagen); //Aparece el nuevo nodo.
        productoInsertar.setSiguiente(primero); // Al nuevo nodo se le pone el siguiente al que está de primero
        // para no perder la secuencia previa.
        setPrimero(productoInsertar); // El nuevo nodo, ya conectado con todos los contenidos previos de la lista,
        // se declara nuevo primero.
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public void insertarNodoFinal(String nombre, String codigo, byte stock, double precio, String categoria, LocalDate fechaDeVencimiento, String imagen) {
        Producto productoInsertar = new Producto(codigo, nombre, stock, precio, categoria, fechaDeVencimiento, imagen);
        if(estaVacia()) {
            setPrimero(productoInsertar);
            return;
        }
        Producto temp = primero;
        while(temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(productoInsertar);
    }
    public Producto buscar (String codigo) {
        if(estaVacia()) {
            System.out.println("La lista está vacía.\n");
            return null;
        }

        Producto temp = primero;
        while(temp != null && !temp.getCodigo().equals(codigo)) {
            temp = temp.getSiguiente();
        }
        if(temp == null) {
            System.out.println("El código no se encontró en la lista.\n");
        } else {
            System.out.println("El código se encontró en la lista:\n");
        }
        return temp;
    }
    public Producto eliminar(String codigo) {
        if(estaVacia()) {
            System.out.println("La lista está vacía.\n");
            return null;
        }
        Producto temp = primero;
        Producto anteriorTemp = primero;
        while(temp != null && !temp.getCodigo().equals(codigo)) {
            anteriorTemp = temp;
            temp = temp.getSiguiente();
        }
        if(temp == null) {
            System.out.println("El código no se encontró en la lista.\n");
        } else {
            System.out.println("El código se encontró en la lista:\n");
            anteriorTemp.setSiguiente(temp.getSiguiente());
        }
        return temp;
    }

    public void mostrarLista() {
        if(estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        Producto temp = primero;
        while(temp != null) {
            System.out.println(temp);
            temp = temp.getSiguiente();
        }
    }



}

