package com.example.nevada26.agendaapp.Modelo;

/**
 * Created by Luis Lavado on 11/05/2016.
 */
public class TipoCorreo {
    private String id_tipo_correo;
    private String descripcion;

    public TipoCorreo() {
    }

    public TipoCorreo(String id_tipo_correo, String descripcion) {
        this.setId_tipo_correo(id_tipo_correo);
        this.setDescripcion(descripcion);
    }

    public String getId_tipo_correo() {
        return id_tipo_correo;
    }

    public void setId_tipo_correo(String id_tipo_correo) {
        this.id_tipo_correo = id_tipo_correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
