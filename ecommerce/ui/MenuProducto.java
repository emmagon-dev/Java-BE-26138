package ui;

import exception.ProductoNoEncontrado;
import exception.StockInsuficiente;
import model.Producto;
import service.ProductoService;
import util.Validador;

import java.util.Scanner;

public class MenuProducto {

    private Scanner scanner;
    private ProductoService service;

    public MenuProducto() {
        scanner = new Scanner(System.in);
        service = new ProductoService();
    }

    public void iniciar() {

        int opcion;

        do {

            mostrarMenu();

            opcion = Validador.leerEntero(scanner);

            switch (opcion) {

                case 1:
                    agregarProducto();
                    break;

                case 2:
                    service.listarProductos();
                    break;

                case 3:
                    buscarActualizarProducto();
                    break;

                case 4:
                    eliminarProducto();
                    break;

                case 5:
                    crearPedido();
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    private void mostrarMenu() {

        System.out.println("\n===================================");
        System.out.println(" SISTEMA DE GESTIÓN - TECHLAB ");
        System.out.println("===================================");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear pedido");
        System.out.println("6) Salir");
        System.out.print("Elija una opción: ");
    }

    private void agregarProducto() {

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = Validador.leerDouble(scanner);

        System.out.print("Stock: ");
        int stock = Validador.leerEntero(scanner);

        Producto producto = new Producto(nombre, precio, stock);

        service.agregarProducto(producto);

        System.out.println("Producto agregado correctamente.");
    }

    private void buscarActualizarProducto() {

        try {

            System.out.print("Ingrese ID del producto: ");
            int id = Validador.leerEntero(scanner);

            Producto producto = service.buscarProductoPorId(id);

            System.out.println(producto);

            System.out.println("1) Actualizar precio");
            System.out.println("2) Actualizar stock");

            int opcion = Validador.leerEntero(scanner);

            switch (opcion) {

                case 1:

                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio =
                            Validador.leerDouble(scanner);

                    service.actualizarPrecio(id, nuevoPrecio);

                    System.out.println("Precio actualizado.");
                    break;

                case 2:

                    System.out.print("Nuevo stock: ");
                    int nuevoStock =
                            Validador.leerEntero(scanner);

                    service.actualizarStock(id, nuevoStock);

                    System.out.println("Stock actualizado.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } catch (ProductoNoEncontrado e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarProducto() {

        try {

            System.out.print("Ingrese ID a eliminar: ");

            int id = Validador.leerEntero(scanner);

            service.eliminarProducto(id);

            System.out.println("Producto eliminado.");

        } catch (ProductoNoEncontrado e) {
            System.out.println(e.getMessage());
        }
    }

    private void crearPedido() {

        try {

            System.out.print("Ingrese ID del producto: ");
            int id = Validador.leerEntero(scanner);

            System.out.print("Cantidad: ");
            int cantidad = Validador.leerEntero(scanner);

            service.crearPedido(id, cantidad);

        } catch (ProductoNoEncontrado | StockInsuficiente e) {
            System.out.println(e.getMessage());
        }
    }
}