public class Cliente {

    private int ClienteID;
    private String nombreCliente;
    private byte prioridad;

    // Constructor

    public Cliente(int clienteID, String nombreCliente, byte prioridad) {
        ClienteID = clienteID;
        this.nombreCliente = nombreCliente;
        this.prioridad = prioridad;
    }

    // Getters

    public int getClienteID() {
        return ClienteID;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public byte getPrioridad() {
        return prioridad;
    }
}
