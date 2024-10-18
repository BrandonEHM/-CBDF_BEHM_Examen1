package com.upiiz.empleados.models;

import java.time.LocalDate;

public class Empleado {
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fecha;
    private String cargo;

    // Constructor vacío requerido por Jackson
    public Empleado() {
    }

    public Empleado(Long id, String nombre, String apellido, LocalDate fecha, String cargo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
