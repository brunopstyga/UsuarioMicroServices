package org.proyectdemo.Util;

public class ItemDataTransferObjects {
    private Long id;
    private String label;

    public ItemDataTransferObjects(Long id, String nombre, String apellido) {
        this.id = id;
        this.label = nombre + " " + apellido; // ya preparado para picker
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
