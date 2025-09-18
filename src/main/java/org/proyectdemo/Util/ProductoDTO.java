package org.proyectdemo.Util;

public class ProductoDTO {
    private String id;
    private String label;
    private int cantidad;
    private double precioUnitario;

    public ProductoDTO(String id, String nombre, int cantidad, double precioUnitario) {
        this.id = id;
        this.label = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getId() { return id; }
    public String getLabel() { return label; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
}
