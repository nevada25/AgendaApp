package com.example.nevada26.agendaapp.Modelo;

/**
 * Created by Luis Lavado on 11/05/2016.
 */
public class PersonaCorreoTel {
    private String idpercorreo;
    private String id_correo;
    private String id_area;
    private String id_telefono;
    private String id_persona;

    public PersonaCorreoTel() {
    }

    public PersonaCorreoTel(String idpercorreo, String id_correo, String id_area, String id_telefono, String id_persona) {
        this.setIdpercorreo(idpercorreo);
        this.setId_correo(id_correo);
        this.setId_area(id_area);
        this.setId_telefono(id_telefono);
        this.setId_persona(id_persona);
    }

    public String getIdpercorreo() {
        return idpercorreo;
    }

    public void setIdpercorreo(String idpercorreo) {
        this.idpercorreo = idpercorreo;
    }

    public String getId_correo() {
        return id_correo;
    }

    public void setId_correo(String id_correo) {
        this.id_correo = id_correo;
    }

    public String getId_area() {
        return id_area;
    }

    public void setId_area(String id_area) {
        this.id_area = id_area;
    }

    public String getId_telefono() {
        return id_telefono;
    }

    public void setId_telefono(String id_telefono) {
        this.id_telefono = id_telefono;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }
}
