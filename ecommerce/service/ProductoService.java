package service;

import exception.ProductoNoEncontrado;
import exception.StockInsuficiente;
import model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private List<Producto> productos;

    public ProductoService() {
        productos = new ArrayList<>();
    }

    // AGREGAR PRODUCTO
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // LISTAR PRODUCTOS
    public void listarProductos() {

        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    // BUSCAR PRODUCTO
    public Producto buscarProductoPorId(int id)
            throws ProductoNoEncontrado {

        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }

        throw new ProductoNoEncontrado(
                "No se encontró un producto con ID " + id
        );
    }

    // ACTUALIZAR PRECIO
    public void actualizarPrecio(int id, double nuevoPrecio)
            throws ProductoNoEncontrado {

        Producto producto = buscarProductoPorId(id);

        if (nuevoPrecio > 0) {
            producto.setPrecio(nuevoPrecio);
        }
    }

    // ACTUALIZAR STOCK
    public void actualizarStock(int id, int nuevoStock)
            throws ProductoNoEncontrado {

        Producto producto = buscarProductoPorId(id);

        if (nuevoStock >= 0) {
            producto.setStock(nuevoStock);
        }
    }

    // ELIMINAR PRODUCTO
    public void eliminarProducto(int id)
            throws ProductoNoEncontrado {

        Producto producto = buscarProductoPorId(id);

        productos.remove(producto);
    }

    // CREAR PEDIDO
    public void crearPedido(int idProducto, int cantidad)
            throws ProductoNoEncontrado, StockInsuficiente {

        Producto producto = buscarProductoPorId(idProducto);

        if (cantidad > producto.getStock()) {
            throw new StockInsuficiente(
                    "Stock insuficiente para el producto: "
                            + producto.getNombre()
            );
        }

        double total = producto.getPrecio() * cantidad;

        producto.setStock(producto.getStock() - cantidad);

        System.out.println("Pedido realizado correctamente.");
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Total: $" + total);
    }
}