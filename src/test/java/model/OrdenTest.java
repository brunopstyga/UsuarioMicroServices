package model;

import org.junit.jupiter.api.Test;
import org.proyectdemo.model.Orden;
import org.proyectdemo.model.Producto;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OrdenTest {
    @Test
    void crearOrdenDebeAsignarCampos() {
        Producto producto = new Producto();
        producto.setProductoId("A123");
        producto.setNombre("Teclado Mecánico");
        producto.setCantidad(1);
        producto.setPrecioUnitario(120.50);

        Orden orden = new Orden();
        orden.setOrdenId(101L);
        orden.setFecha("2025-09-10");
        orden.setTotal(250.75);
        orden.setProductos(List.of(producto));

        assertEquals(101L, orden.getOrdenId());
        assertEquals("2025-09-10", orden.getFecha());
        assertEquals(250.75, orden.getTotal());
        assertEquals(1, orden.getProductos().size());
        assertEquals("Teclado Mecánico", orden.getProductos().get(0).getNombre());
    }
}
