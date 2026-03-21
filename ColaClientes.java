import java.util.ArrayList;

public class ColaClientes {

    private ArrayList<Cliente> colaClientes;

    public ColaClientes() {
        colaClientes = new ArrayList<>();
    }

    public boolean estaVacia() {
        return colaClientes.isEmpty();
    }

    public void encolar(Cliente cliente) {
        if (cliente != null) {
            colaClientes.add(cliente);
        }
    }

    public Cliente desencolar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        }

        int indiceMayorPrioridad = 0;

        for (int i = 1; i < colaClientes.size(); i++) {
            if (colaClientes.get(i).getPrioridad() > colaClientes.get(indiceMayorPrioridad).getPrioridad()) {
                indiceMayorPrioridad = i;
            }
        }

        return colaClientes.remove(indiceMayorPrioridad);
    }

    public Cliente verSiguiente() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        }

        int indiceMayorPrioridad = 0;

        for (int i = 1; i < colaClientes.size(); i++) {
            if (colaClientes.get(i).getPrioridad() > colaClientes.get(indiceMayorPrioridad).getPrioridad()) {
                indiceMayorPrioridad = i;
            }
        }

        return colaClientes.get(indiceMayorPrioridad);
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : colaClientes) {
            if (cliente.getClienteID() == id) {
                return cliente;
            }
        }
        return null;
    }

    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        System.out.println("=========== COLA DE CLIENTES ===========");
        for (Cliente cliente : colaClientes) {
            System.out.println(cliente);
        }
        System.out.println("========================================");
    }
}