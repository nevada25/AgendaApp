package com.example.nevada26.agendaapp.Modelo;

/**
 * Created by Luis Lavado on 11/05/2016.
 */
public class TipoEmpresa {
    private String id_tipo_empresa;
    private String nombre_te;
    private String descripcion;

    public TipoEmpresa() {
    }

    public TipoEmpresa(String id_tipo_empresa, String nombre_te, String descripcion) {
        this.setId_tipo_empresa(id_tipo_empresa);
        this.setNombre_te(nombre_te);
        this.setDescripcion(descripcion);
    }

    public String getId_tipo_empresa() {
        return id_tipo_empresa;
    }

    public void setId_tipo_empresa(String id_tipo_empresa) { this.id_tipo_empresa = id_tipo_empresa; }

    public String getNombre_te() {
        return nombre_te;
    }

    public void setNombre_te(String nombre_te) {
        this.nombre_te = nombre_te;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
