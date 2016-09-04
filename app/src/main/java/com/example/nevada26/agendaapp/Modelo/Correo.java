package com.example.nevada26.agendaapp.Modelo;

public class Correo {
    private String id_correo;
    private String id_tipo_correo;
    private String id_persona;
    private String correo;
    private String estado;

    public Correo() {
    }

    public Correo(String id_correo, String id_tipo_correo, String id_persona, String correo, String estado) {
        this.setId_correo(id_correo);
        this.setId_tipo_correo(id_tipo_correo);
        this.setId_persona(id_persona);
        this.setCorreo(correo);
        this.setEstado(estado);
    }

    public String getId_correo() {
        return id_correo;
    }

    public void setId_correo(String id_correo) {
        this.id_correo = id_correo;
    }

    public String getId_tipo_correo() {
        return id_tipo_correo;
    }

    public void setId_tipo_correo(String id_tipo_correo) {
        this.id_tipo_correo = id_tipo_correo;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
