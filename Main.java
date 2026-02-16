import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {

    public static void main(String[] args) {

        // CRUD -> Create, read, update, delete.

        ListaProducto listaProductos = new ListaProducto();

        listaProductos.insertarProductoFinal("001", "Pan", (byte) 20, (double)500, "Pan", 2026/05/01, "Imagen 1");
        listaProductos.insertarProductoFinal("002", "Pan con queso", (byte) 20, (double)550, "Pan", 2026-05-01, "Imagen 2");
        listaProductos.insertarProductoFinal("003", "Pastel de pollo", (byte) 5, (double)700, "Repostería", 2026-05-04, "Imagen 3");
        listaProductos.insertarProductoFinal("004", "Pastel de carne", (byte) 5, (double)700, "Repostería", 2026-05-02, "Imagen 4");
        listaProductos.insertarProductoFinal("005", "Flauta de Guayaba", (byte) 5, (double)700, "Repostería", 2026-05-03, "Imagen 5");
        listaProductos.insertarProductoFinal("006", "Pañelo", (byte) 5, (double)700, "Repostería", 2026-05-04, "Imagen 6");
        listaProductos.insertarProductoFinal("007", "Leche", (byte) 5, (double)700, "Abarrotes", 2026-05-15, "Imagen 7");
        listaProductos.insertarProductoFinal("008", "Mantequilla", (byte) 5, (double)700, "Abarrotes", 2026-05-15, "Imagen 8");
        listaProductos.insertarProductoFinal("009", "Paté", (byte) 5, (double)700, "Abarrotes", 2026-05-15, "Imagen 9");
        listaProductos.insertarProductoFinal("010", "Natilla", (byte) 5, (double)700, "Abarrotes", 2026-05-15, "Imagen 10");

        listaProductos.mostrarLista();

        //System.out.println(listaProductos.buscar("SOFT-10"));


        /* Producto primerProducto = new Producto("Programación Orientada a Objetos","SOFT-04",(byte)5); //Creación/Construcción de un objeto.

        Producto segundoProducto = new Producto("Estructura de Datos", "SOFT-10", (byte)3);

        System.out.println(segundoProducto.getNombre());

        // primerProducto.nombre = "POO"; <<< Esto no se puede hacer porque los atributos del objeto son privados.
        // /hay que usarlo con setNombre.

        primerProducto.setNombre("POO");

        System.out.println(primerProducto.getNombre());
        System.out.println(primerProducto);
        System.out.println(segundoProducto); */

    }

}

