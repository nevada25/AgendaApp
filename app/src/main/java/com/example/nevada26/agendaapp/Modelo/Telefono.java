package com.example.nevada26.agendaapp.Modelo;

/**
 * Created by Luis Lavado on 11/05/2016.
 */
public class Telefono {
    private String id_telefono;
    private String id_operador;
    private String id_persona;
    private String descripcion;
    private String nro_telefono;
    private String estado;

    public Telefono() {
    }

    public Telefono(String id_telefono, String id_operador, String id_persona, String descripcion, String nro_telefono, String estado) {
        this.setId_telefono(id_telefono);
        this.setId_operador(id_operador);
        this.setId_persona(id_persona);
        this.setDescripcion(descripcion);
        this.setNro_telefono(nro_telefono);
        this.setEstado(estado);
    }

    public String getId_telefono() {
        return id_telefono;
    }

    public void setId_telefono(String id_telefono) {
        this.id_telefono = id_telefono;
    }

    public String getId_operador() {
        return id_operador;
    }

    public void setId_operador(String id_operador) {
        this.id_operador = id_operador;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNro_telefono() {
        return nro_telefono;
    }

    public void setNro_telefono(String nro_telefono) {
        this.nro_telefono = nro_telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
