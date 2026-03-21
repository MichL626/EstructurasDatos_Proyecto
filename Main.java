import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Tienda tienda = new Tienda("Tienda Virtual");

        cargarProductosIniciales(tienda);

        menu(tienda);

        sc.close();
    }

    public static void menu(Tienda tienda) {
        int opcion;

        do {
            imprimirMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> agregarProductoInventario(tienda);
                case 2 -> mostrarInventario(tienda);
                case 3 -> registrarCliente(tienda);
                case 4 -> mostrarColaClientes(tienda);
                case 5 -> agregarProductoCarrito(tienda);
                case 6 -> atenderSiguienteCliente(tienda);
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }

            System.out.println();
        } while (opcion != 0);
    }

    private static void imprimirMenu() {
        System.out.println("=========================================");
        System.out.println("     SISTEMA DE VENTAS EN LÍNEA");
        System.out.println("=========================================");
        System.out.println("1. Agregar producto al inventario");
        System.out.println("2. Mostrar inventario");
        System.out.println("3. Registrar cliente en la cola");
        System.out.println("4. Mostrar cola de clientes");
        System.out.println("5. Agregar producto al carrito de un cliente");
        System.out.println("6. Atender siguiente cliente");
        System.out.println("0. Salir");
        System.out.println("=========================================");
    }

    private static void agregarProductoInventario(Tienda tienda) {
        System.out.println("---- Agregar producto al inventario ----");

        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        int stock = leerEnteroNoNeg("Stock: ");
        double precio = leerDoubleNoNeg("Precio: ");
        String categoria = leerTexto("Categoría: ");
        LocalDate fecha = leerFechaOpcional("Fecha de vencimiento (YYYY-MM-DD) o ENTER si no aplica: ");

        boolean agregado = tienda.agregarProductoAlInventario(
                codigo, nombre, stock, precio, categoria, fecha
        );

        if (agregado) {
            System.out.println("Producto agregado correctamente al inventario.");
        } else {
            System.out.println("No se pudo agregar el producto. Puede estar repetido por nombre.");
        }
    }

    private static void mostrarInventario(Tienda tienda) {
        System.out.println("---- Inventario de la tienda ----");
        tienda.getInventario().mostrarInOrden();
    }

    private static void registrarCliente(Tienda tienda) {
        System.out.println("---- Registrar cliente ----");

        int id = leerEnteroNoNeg("ID del cliente: ");
        String nombre = leerTexto("Nombre del cliente: ");
        byte prioridad = leerPrioridad("Prioridad (1 = Básico, 2 = Afiliado, 3 = Premium): ");

        boolean registrado = tienda.registrarCliente(id, nombre, prioridad);

        if (registrado) {
            System.out.println("Cliente registrado correctamente en la cola.");
        } else {
            System.out.println("No se pudo registrar el cliente. Puede que el ID ya exista.");
        }
    }

    private static void mostrarColaClientes(Tienda tienda) {
        System.out.println("---- Cola de clientes ----");
        tienda.getColaClientes().mostrarCola();
    }

    private static void agregarProductoCarrito(Tienda tienda) {
        System.out.println("---- Agregar producto al carrito ----");

        int idCliente = leerEnteroNoNeg("ID del cliente: ");
        String nombreProducto = leerTexto("Nombre del producto: ");
        int cantidad = leerEnteroNoNeg("Cantidad deseada: ");

        boolean agregado = tienda.agregarProductoAlCarrito(idCliente, nombreProducto, cantidad);

        if (agregado) {
            System.out.println("Producto agregado al carrito correctamente.");
        } else {
            System.out.println("No se pudo agregar el producto al carrito.");
            System.out.println("Verifique que el cliente exista, que el producto exista y que haya stock suficiente.");
        }
    }

    private static void atenderSiguienteCliente(Tienda tienda) {
        System.out.println("---- Atender siguiente cliente ----");

        Cliente clienteAtendido = tienda.atenderSiguienteCliente();

        if (clienteAtendido == null) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        System.out.println("Cliente atendido:");
        System.out.println(clienteAtendido);

        System.out.println();
        tienda.imprimirFactura(clienteAtendido);
    }

    // MÉTODOS DE APOYO
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static int leerEnteroNoNeg(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor < 0) {
                System.out.println("No se permiten valores negativos.");
            } else {
                return valor;
            }
        }
    }

    private static double leerDoubleNoNeg(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                if (valor < 0) {
                    System.out.println("No se permiten valores negativos.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número decimal válido.");
            }
        }
    }

    private static LocalDate leerFechaOpcional(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                return null;
            }

            try {
                return LocalDate.parse(entrada);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use YYYY-MM-DD.");
            }
        }
    }

    private static byte leerPrioridad(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);

            if (valor >= 1 && valor <= 3) {
                return (byte) valor;
            }

            System.out.println("La prioridad debe ser 1, 2 o 3.");
        }
    }

    private static void cargarProductosIniciales(Tienda tienda) {
        tienda.agregarProductoAlInventario("001", "Pan", 20, 500, "Panadería",
                LocalDate.parse("2026-05-01"));

        tienda.agregarProductoAlInventario("002", "Pan con queso", 20, 550, "Panadería",
                LocalDate.parse("2026-05-01"));

        tienda.agregarProductoAlInventario("003", "Pastel de pollo", 5, 700, "Repostería",
                LocalDate.parse("2026-05-04"));

        tienda.agregarProductoAlInventario("004", "Pastel de carne", 5, 700, "Repostería",
                LocalDate.parse("2026-05-05"));

        tienda.agregarProductoAlInventario("005", "Flauta de guayaba", 5, 700, "Repostería",
                LocalDate.parse("2026-05-03"));

        tienda.agregarProductoAlInventario("006", "Pañuelo", 5, 700, "Repostería",
                LocalDate.parse("2026-05-02"));

        tienda.agregarProductoAlInventario("007", "Leche", 5, 900, "Abarrotes",
                LocalDate.parse("2026-05-15"));

        tienda.agregarProductoAlInventario("008", "Mantequilla", 5, 1200, "Abarrotes",
                LocalDate.parse("2026-05-15"));

        tienda.agregarProductoAlInventario("009", "Paté", 5, 850, "Abarrotes",
                LocalDate.parse("2026-05-15"));

        tienda.agregarProductoAlInventario("010", "Natilla", 5, 950, "Abarrotes",
                LocalDate.parse("2026-05-15"));
    }
}