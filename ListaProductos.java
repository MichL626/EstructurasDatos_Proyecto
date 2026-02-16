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


    //Insertar productos Final
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

    //Buscar por código
    public Producto buscar(String codigo) {
        if (estaVacia()) return null;

        Producto temp = primero;
        while (temp != null && !Objects.equals(temp.getCodigo(), codigo)) {
            temp = temp.getSiguiente();
        }
        return temp;
    }

    //Modificar
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

    //Eliminar por Código
    public Producto eliminar(String codigo) {
        if (estaVacia()) return null;

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

    //Reporte Costos

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
            System.out.println("Subtotal (cantidad * precio): " + subtotal);
            System.out.println("--------------------------------------");

            temp = temp.getSiguiente();
        }

        System.out.println("TOTAL ACUMULADO DE LA LISTA: " + totalAcumulado);
        System.out.println("======================================");
    }

}

