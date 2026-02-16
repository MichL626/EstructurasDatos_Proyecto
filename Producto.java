import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Producto {

    // Atributos

    private String codigo;
    private String nombre;
    private int cantidad;
    private int stock;
    private double precio;
    private String categoria;
    private LocalDate fechaDeVencimiento;
    private String imagen;
    private ArrayList<String> listaImagenes;
    private Producto siguiente;


    // Constructores

    public Producto() {
        this.listaImagenes = new ArrayList<>();
        this.siguiente = null;
    }

    public Producto(String codigo, String nombre, int cantidad, int stock, double precio,
                    String categoria, LocalDate fechaDeVencimiento, ArrayList<String> listaImagenes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.listaImagenes = (listaImagenes == null) ? new ArrayList<>() : listaImagenes;
        this.siguiente = null;
    }

    // Getters y Setters

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public LocalDate getFechaDeVencimiento() { return fechaDeVencimiento; }
    public void setFechaDeVencimiento(LocalDate fechaDeVencimiento) { this.fechaDeVencimiento = fechaDeVencimiento; }

    public ArrayList<String> getListaImagenes() { return listaImagenes; }
    public void setListaImagenes(ArrayList<String> listaImagenes) {
        this.listaImagenes = (listaImagenes == null) ? new ArrayList<>() : listaImagenes;
    }

    public Producto getSiguiente() { return siguiente; }
    public void setSiguiente(Producto siguiente) { this.siguiente = siguiente; }


    public void agregarImagen(String ruta) {
        if (ruta == null) return;
        ruta = ruta.trim();
        if (!ruta.isEmpty()) {
            listaImagenes.add(ruta);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", stock=" + stock +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", fechaDeVencimiento=" + fechaDeVencimiento +
                ", imagenes=" + listaImagenes +
                '}';
    }

}
