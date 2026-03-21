public class Cliente {

    private int clienteID;
    private String nombreCliente;
    private byte prioridad;
    private ListaProductos carrito;

    public Cliente(int clienteID, String nombreCliente, byte prioridad) {
        this.clienteID = clienteID;
        this.nombreCliente = nombreCliente;
        setPrioridad(prioridad);
        this.carrito = new ListaProductos();
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public byte getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(byte prioridad) {
        if (prioridad >= 1 && prioridad <= 3) {
            this.prioridad = prioridad;
        } else {
            this.prioridad = 1;
        }
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public String getTipoPrioridad() {
        return switch (prioridad) {
            case 1 -> "Básico";
            case 2 -> "Afiliado";
            case 3 -> "Premium";
            default -> "Desconocido";
        };
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteID=" + clienteID +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", prioridad=" + prioridad +
                " (" + getTipoPrioridad() + ")" +
                '}';
    }
}