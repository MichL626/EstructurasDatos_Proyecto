import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ListaProducto lista = new ListaProducto();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    insertar(lista, true);
                    break;

                case 2:
                    insertar(lista, false);
                    break;

                case 3:
                    lista.mostrarLista();
                    break;

                case 4:
                    buscar(lista);
                    break;

                case 5:
                    modificar(lista);
                    break;

                case 6:
                    eliminar(lista);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

            System.out.println();
        } while (opcion != 0);

        sc.close();
    }

    // ---------------- MENÚ ----------------

    private static void mostrarMenu() {
        System.out.println("====================================");
        System.out.println("     SISTEMA DE GESTIÓN PRODUCTOS    ");
        System.out.println("====================================");
        System.out.println("1. Insertar producto al INICIO ⏮\uFE0F");
        System.out.println("2. Insertar producto al FINAL ⏭\uFE0F");
        System.out.println("3. Mostrar lista de productos \uD83D\uDCC2");
        System.out.println("4. Buscar producto por CÓDIGO \uD83D\uDD0D");
        System.out.println("5. Modificar producto \uD83D\uDCDD");
        System.out.println("6. Eliminar producto ❌");
        System.out.println("0. Salir \uD83C\uDFC1");
        System.out.println("====================================");
    }

    // ---------------- OPERACIONES ----------------

    private static void insertar(ListaProductos lista, boolean inicio) {

        System.out.println(inicio
                ? "--- Insertar producto al INICIO ---"
                : "--- Insertar producto al FINAL ---");

        int codigo = leerEntero("Código: ");


        if (lista.buscarPorCodigo(codigo) != null) {
            System.out.println("⚠ Ya existe un producto con ese código.");
            return;
        }

        String nombre = leerTexto("Nombre: ");
        byte stock = leerByte("Stock: ");
        double precio = leerDouble("Precio: ");
        String categoria = leerTexto("Categoría: ");
        LocalDate fecha = leerFechaOpcional("Fecha vencimiento (YYYY-MM-DD) o ENTER si no aplica: ");
        String imagen = leerTexto("Imagen (ruta o nombre): ");

        if (inicio) {
            lista.insertarInicio(nombre, codigo, stock, precio, categoria, fecha, imagen);
        } else {
            lista.insertarFinal(nombre, codigo, stock, precio, categoria, fecha, imagen);
        }

        System.out.println("Producto insertado correctamente.");
    }

    private static void buscar(ListaProductos lista) {
        System.out.println("--- Buscar producto ---");

        int codigo = leerEntero("Código a buscar: ");
        Producto p = lista.buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("Producto no encontrado.");
        } else {
            System.out.println("Producto encontrado:");
            System.out.println(p);
        }
    }

    private static void modificar(ListaProductos lista) {
        System.out.println("--- Modificar producto ---");

        int codigo = leerEntero("Código del producto: ");
        Producto p = lista.buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Producto actual:");
        System.out.println(p);
        System.out.println("Ingrese los nuevos datos:");

        String nombre = leerTexto("Nombre: ");
        byte stock = leerByte("Stock: ");
        double precio = leerDouble("Precio: ");
        String categoria = leerTexto("Categoría: ");
        LocalDate fecha = leerFechaOpcional("Fecha vencimiento (YYYY-MM-DD) o ENTER si no aplica: ");
        String imagen = leerTexto("Imagen (ruta o nombre): ");

        boolean ok = lista.modificar(codigo, nombre, stock, precio, categoria, fecha, imagen);
        System.out.println(ok ? "Producto modificado." : "Error al modificar.");
    }

    private static void eliminar(ListaProductos lista) {
        System.out.println("--- Eliminar producto ---");

        int codigo = leerEntero("Código a eliminar: ");
        boolean ok = lista.eliminar(codigo);

        System.out.println(ok
                ? "Producto eliminado."
                : "❌ No existe un producto con ese código.");
    }

    // ---------------- LECTURA SEGURA ----------------

    private static String leerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠\uFE0F Ingrese un número entero válido.");
            }
        }
    }

    private static byte leerByte(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v < 0 || v > 127) {
                    System.out.println("⚠\uFE0F El stock debe estar entre 0 y 127.");
                    continue;
                }
                return (byte) v;
            } catch (NumberFormatException e) {
                System.out.println("⚠\uFE0F Ingrese un número válido.");
            }
        }
    }

    private static double leerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("⚠\uFE0F Ingrese un número decimal válido.");
            }
        }
    }

    private static LocalDate leerFechaOpcional(String msg) {
        while (true) {
            System.out.print(msg);
            String in = sc.nextLine().trim();

            if (in.isEmpty()) return null;

            try {
                return LocalDate.parse(in);
            } catch (DateTimeParseException e) {
                System.out.println("⚠\uFE0F Formato inválido. Use YYYY-MM-DD.");
            }
        }
    }
}
