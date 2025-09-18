package org.proyectdemo.model;

import java.util.List;

public class Usuario {
    private long id;
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private boolean activo;
    private List<Orden> ordenes;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public List<Orden> getOrdenes() { return ordenes; }
    public void setOrdenes(List<Orden> ordenes) { this.ordenes = ordenes; }

}
