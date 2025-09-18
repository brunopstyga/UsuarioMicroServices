package org.proyectdemo.Util;

import java.util.List;

public class OrdenDTO {
    private Long id;
    private String label;
    private List<ProductoDTO> productos;

    public OrdenDTO(Long id, String fecha, List<ProductoDTO> productos) {
        this.id = id;
        this.label = "Orden " + id + " - " + fecha; // ya preparado para picker
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }
}
