import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ListaProductos lista = new ListaProductos();

        cargarDatosIniciales(lista);

        int opcion;
        do {
            imprimirMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> insertar(lista, true);
                case 2 -> insertar(lista, false);
                case 3 -> lista.mostrarLista();
                case 4 -> buscar(lista);
                case 5 -> modificar(lista);
                case 6 -> eliminar(lista);
                case 7 -> agregarImagen(lista);
                case 8 -> lista.reporteCostos();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Digite un número de 0 al 8");
            }

            System.out.println();
        } while (opcion != 0);

        sc.close();
    }

    // MENÚ

    private static void imprimirMenu() {
        System.out.println(" ======================================");
        System.out.println("|  SISTEMA - LISTA ENLAZADA PRODUCTOS  |");
        System.out.println(" ======================================");
        System.out.println("1. Insertar producto al INICIO");
        System.out.println("2. Insertar producto al FINAL");
        System.out.println("3. Mostrar lista");
        System.out.println("4. Buscar producto por código");
        System.out.println("5. Modificar producto");
        System.out.println("6. Eliminar producto");
        System.out.println("7. Agregar imagen a producto");
        System.out.println("8. Reporte de costos (subtotal y total)");
        System.out.println("0. Salir");
        System.out.println("=======================================");
    }

    // OPERACIONES

    private static void insertar(ListaProductos lista, boolean inicio) {
        System.out.println(inicio ? "--- Insertar al INICIO ---" : "--- Insertar al FINAL ---");

        String codigo = leerTexto("Código: ");

        if (lista.buscar(codigo) != null) {
            System.out.println("Ya existe un producto con ese código. No se insertó.");
            return;
        }

        String nombre = leerTexto("Nombre: ");
        int cantidad = leerEnteroNoNeg("Cantidad (Para el carrito): ");
        int stock = leerEnteroNoNeg("Stock (inventario tienda): ");
        double precio = leerDoubleNoNeg("Precio unitario: ");
        String categoria = leerTexto("Categoría: ");
        LocalDate fecha = leerFechaOpcional("Fecha vencimiento (YYYY-MM-DD) o ENTER si no aplica: ");

        boolean ok = inicio
                ? lista.insertarInicio(codigo, nombre, cantidad, stock, precio, categoria, fecha)
                : lista.insertarFinal(codigo, nombre, cantidad, stock, precio, categoria, fecha);

        if (!ok) {
            System.out.println("No se pudo insertar (posible código duplicado).");
            return;
        }

        System.out.println("Producto insertado.");

        System.out.println("Puede agregar imágenes ahora (rutas). Escriba 0 para terminar.");
        while (true) {
            String ruta = leerTexto("Ruta imagen (o 0 / ENTER para terminar): ");
            if (ruta.isEmpty() || ruta.equals("0")) break;
            lista.agregarImagenAProducto(codigo, ruta);
        }
    }

    private static void buscar(ListaProductos lista) {
        System.out.println("--- Buscar producto ---");
        String codigo = leerTexto("Código: ");

        Producto p = lista.buscar(codigo);
        if (p == null) {
            System.out.println("No encontrado.");
        } else {
            System.out.println("Encontrado:");
            System.out.println(p);
        }
    }

    private static void modificar(ListaProductos lista) {
        System.out.println("--- Modificar producto ---");

        String codigo = leerTexto("Código del producto a modificar: ");
        Producto p = lista.buscar(codigo);

        if (p == null) {
            System.out.println("No existe un producto con ese código.");
            return;
        }

        System.out.println("Producto actual:");
        System.out.println(p);
        System.out.println("---- Ingrese los nuevos datos ----");

        String nombre = leerTexto("Nuevo nombre: ");
        int cantidad = leerEnteroNoNeg("Nueva cantidad: ");
        int stock = leerEnteroNoNeg("Nuevo stock: ");
        double precio = leerDoubleNoNeg("Nuevo precio: ");
        String categoria = leerTexto("Nueva categoría: ");
        LocalDate fecha = leerFechaOpcional("Nueva fecha vencimiento (YYYY-MM-DD) o ENTER si no aplica: ");

        boolean ok = lista.modificar(codigo, nombre, cantidad, stock, precio, categoria, fecha);
        System.out.println(ok ? "Producto modificado." : "No se pudo modificar.");

        System.out.println("¿Desea agregar imágenes? Escriba 0 para terminar.");
        while (true) {
            String ruta = leerTexto("Ruta imagen (o 0 / ENTER para terminar): ");
            if (ruta.isEmpty() || ruta.equals("0")) break;
            lista.agregarImagenAProducto(codigo, ruta);
            System.out.println("Imagen agregada.");
        }
    }

    private static void eliminar(ListaProductos lista) {
        System.out.println("--- Eliminar producto ---");
        String codigo = leerTexto("Código: ");

        Producto eliminado = lista.eliminar(codigo);
        if (eliminado == null) {
            System.out.println("No existe un producto con ese código.");
        } else {
            System.out.println("Producto eliminado:");
            System.out.println(eliminado);
        }
    }

    private static void agregarImagen(ListaProductos lista) {
        System.out.println("--- Agregar imagen a producto ---");
        String codigo = leerTexto("Código del producto: ");

        Producto p = lista.buscar(codigo);
        if (p == null) {
            System.out.println("No existe un producto con ese código.");
            return;
        }

        System.out.println("Producto encontrado:");
        System.out.println(p);

        System.out.println("Agregue imágenes (rutas). Escriba 0 para terminar.");
        while (true) {
            String ruta = leerTexto("Ruta imagen (o 0 / ENTER para terminar): ");
            if (ruta.isEmpty() || ruta.equals("0")) break;

            boolean ok = lista.agregarImagenAProducto(codigo, ruta);
            System.out.println(ok ? "Imagen agregada." : "No se pudo agregar.");
        }
    }

    // LECTURA SEGURA
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
                System.out.println("⚠ Ingrese un número entero válido.");
            }
        }
    }

    private static int leerEnteroNoNeg(String msg) {
        while (true) {
            int v = leerEntero(msg);
            if (v < 0) {
                System.out.println("⚠ No se permiten valores negativos.");
                continue;
            }
            return v;
        }
    }

    private static double leerDoubleNoNeg(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double v = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                if (v < 0) {
                    System.out.println("⚠ No se permiten valores negativos.");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingrese un número decimal válido.");
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
                System.out.println("Formato inválido. Use YYYY-MM-DD o ENTER si no aplica.");
            }
        }
    }

    private static void cargarDatosIniciales(ListaProductos lista) {

        lista.insertarFinal("001", "Pan", 2, 20, 500, "Pan",
                LocalDate.parse("2026-05-01"));
        lista.agregarImagenAProducto("001", "Imagen 1");

        lista.insertarFinal("002", "Pan con queso", 2, 20, 550, "Pan",
                LocalDate.parse("2026-05-01"));
        lista.agregarImagenAProducto("002", "Imagen 2");

        lista.insertarFinal("003", "Pastel de pollo", 1, 5, 700, "Repostería",
                LocalDate.parse("2026-05-04"));
        lista.agregarImagenAProducto("003", "Imagen 3");

        lista.insertarFinal("004", "Pastel de carne", 1, 5, 700, "Repostería",
                LocalDate.parse("2026-05-05"));
        lista.agregarImagenAProducto("004", "Imagen 4");

        lista.insertarFinal("005", "Flauta de Guayaba", 1, 5, 700, "Repostería",
                LocalDate.parse("2026-05-03"));
        lista.agregarImagenAProducto("005", "Imagen 5");

        lista.insertarFinal("006", "Pañelo", 1, 5, 700, "Repostería",
                LocalDate.parse("2026-05-02"));
        lista.agregarImagenAProducto("006", "Imagen 6");

        lista.insertarFinal("007", "Leche", 1, 5, 700, "Abarrotes",
                LocalDate.parse("2026-05-15"));
        lista.agregarImagenAProducto("007", "Imagen 7");

        lista.insertarFinal("008", "Mantequilla", 1, 5, 700, "Abarrotes",
                LocalDate.parse("2026-05-15"));
        lista.agregarImagenAProducto("008", "Imagen 8");

        lista.insertarFinal("009", "Paté", 1, 5, 700, "Abarrotes",
                LocalDate.parse("2026-05-15"));
        lista.agregarImagenAProducto("009", "Imagen 9");

        lista.insertarFinal("010", "Natilla", 1, 5, 700, "Abarrotes",
                LocalDate.parse("2026-05-15"));
        lista.agregarImagenAProducto("010", "Imagen 10");

    }
}
