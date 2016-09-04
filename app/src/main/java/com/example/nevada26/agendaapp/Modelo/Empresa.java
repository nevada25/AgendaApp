package com.example.nevada26.agendaapp.Modelo;

/**
 * Created by Luis Lavado on 11/05/2016.
 */
public class Empresa {
    private String id_empresa;
    private String id_tipo_empresa;
    private String descripcion;
    private String estado;
    private String id_padre_empresa;

    public Empresa() {
    }

    public Empresa(String id_empresa, String id_tipo_empresa, String descripcion, String estado, String id_padre_empresa) {
        this.setId_empresa(id_empresa);
        this.setId_tipo_empresa(id_tipo_empresa);
        this.setDescripcion(descripcion);
        this.setEstado(estado);
        this.setId_padre_empresa(id_padre_empresa);
    }

    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getId_tipo_empresa() {
        return id_tipo_empresa;
    }

    public void setId_tipo_empresa(String id_tipo_empresa) {
        this.id_tipo_empresa = id_tipo_empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_padre_empresa() {
        return id_padre_empresa;
    }

    public void setId_padre_empresa(String id_padre_empresa) {
        this.id_padre_empresa = id_padre_empresa;
    }
}
