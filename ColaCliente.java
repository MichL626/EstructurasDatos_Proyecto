import java.util.ArrayList;

public class ColaCliente {

    // Atributos
    private ArrayList<String> colaClientes;

    // Métodos
    // Constructor


    public ColaCliente() {
        colaClientes = new ArrayList<>();
    }

    public void push(String clienteID) {
        colaClientes.add(clienteID);
    }

    public String pop() {
        if (colaClientes.isEmpty()) {
            System.out.println("Lo sentimos, la cola está vacía.");
            return null;
        }
        return colaClientes.removeLast();
    }
    public String peek() {
        if (colaClientes.isEmpty()) {
            System.out.println("Lo sentimos, la cola está vacía.");
            return null;
        }
        return colaClientes.getLast();
    }
}
