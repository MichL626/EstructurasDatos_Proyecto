import java.time.LocalDate;
import java.util.Objects;

public class Producto {

    // Atributos

    private String codigo;
    private String nombre;
    private byte stock;
    private double precio;
    private String categoria;
    private LocalDate fechaDeVencimiento;
    private String imagen;
    private Producto siguiente;

    // Métodos


    public Producto(String nombre, String codigo, byte stock, double precio, String categoria, LocalDate fechaDeVencimiento, String imagen) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.imagen = imagen;
        this.siguiente = null;
    }

    // Getters

    public Producto() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getStock() {
        return stock;
    }

    public void setStock(byte stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(LocalDate fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Producto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Producto siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return codigo == producto.codigo && stock == producto.stock && Double.compare(precio, producto.precio) == 0 && Objects.equals(nombre, producto.nombre) && Objects.equals(categoria, producto.categoria) && Objects.equals(fechaDeVencimiento, producto.fechaDeVencimiento) && Objects.equals(imagen, producto.imagen) && Objects.equals(siguiente, producto.siguiente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, stock, precio, categoria, fechaDeVencimiento, imagen, siguiente);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", fechaDeVencimiento=" + fechaDeVencimiento +
                ", imagen='" + imagen + '\'' +
                ", siguiente=" + siguiente +
                '}';
    }
}
