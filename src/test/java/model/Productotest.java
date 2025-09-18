package model;

import org.junit.jupiter.api.Test;
import org.proyectdemo.model.Producto;

import static org.junit.jupiter.api.Assertions.*;
public class Productotest {
    @Test
    void crearProductoDebeAsignarCampos() {
        Producto producto = new Producto();
        producto.setProductoId("B456");
        producto.setNombre("Mouse Inalámbrico");
        producto.setCantidad(1);
        producto.setPrecioUnitario(130.25);

        assertEquals("B456", producto.getProductoId());
        assertEquals("Mouse Inalámbrico", producto.getNombre());
        assertEquals(1, producto.getCantidad());
        assertEquals(130.25, producto.getPrecioUnitario());
    }
}
