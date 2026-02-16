import java.time.LocalDate;
import java.util.Objects;

public class ListaProductos {

    //Atributos
    private Producto primero;

    //Constructor
    public ListaProductos() {
        this.primero = null;
    }

    //Getters y Setters
    public Producto getPrimero() {
        return primero;
    }

    public void setPrimero(Producto primero) {
        this.primero = primero;
    }


    private boolean estaVacia() {
        return primero == null;
    }

    //Métodos

    //Insertar Productos Inicio

    //Con datos
    public void insertarProductoInicio(String nombre, String codigo, byte stock, double precio,
                                       String categoria, LocalDate fechaDeVencimiento, String imagen) {

        Producto productoInsertar = new Producto(nombre, codigo, stock, precio, categoria, fechaDeVencimiento, imagen);
        productoInsertar.setSiguiente(primero);
        setPrimero(productoInsertar);
    }

    //Recibe Información
    public void insertarInicio(Producto nuevo) {
        if (nuevo == null) return;
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    //Insertar Final

    //Con Datos
    public void insertarProductoFinal(String nombre, String codigo, byte stock, double precio,
                                      String categoria, LocalDate fechaDeVencimiento, String imagen) {

        Producto productoInsertar = new Producto(nombre, codigo, stock, precio, categoria, fechaDeVencimiento, imagen);

        if (estaVacia()) {
            setPrimero(productoInsertar);
            return;
        }

        Producto temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(productoInsertar);
    }


    //Recibe Información
    public void insertarFinal(Producto nuevo) {
        if (nuevo == null) return;

        if (estaVacia()) {
            primero = nuevo;
            return;
        }

        Producto aux = primero;
        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
        }
        aux.setSiguiente(nuevo);
    }

    //Buscar por código
    public Producto buscar(String codigo) {
        if (estaVacia()) {
            System.out.println("La lista está vacía.\n");
            return null;
        }

        Producto temp = primero;
        while (temp != null && !Objects.equals(temp.getCodigo(), codigo)) {
            temp = temp.getSiguiente();
        }

        if (temp == null) {
            System.out.println("El código no se encontró en la lista.\n");
        } else {
            System.out.println("El código se encontró en la lista:\n");
        }

        return temp;
    }

    //Eliminar por Código
    public Producto eliminar(String codigo) {
        if (estaVacia()) {
            System.out.println("La lista está vacía.\n");
            return null;
        }

        // Caso 1: eliminar el primero
        if (Objects.equals(primero.getCodigo(), codigo)) {
            Producto eliminado = primero;
            primero = primero.getSiguiente();
            eliminado.setSiguiente(null); // opcional: desconectar
            System.out.println("El código se encontró en la lista:\n");
            return eliminado;
        }

        // Caso 2: eliminar en medio o final
        Producto anterior = primero;
        Producto actual = primero.getSiguiente();

        while (actual != null && !Objects.equals(actual.getCodigo(), codigo)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) {
            System.out.println("El código no se encontró en la lista.\n");
            return null;
        }

        anterior.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(null); // opcional
        System.out.println("El código se encontró en la lista:\n");
        return actual;
    }

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





}

