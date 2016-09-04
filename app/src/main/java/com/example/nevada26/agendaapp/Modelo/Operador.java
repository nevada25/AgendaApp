package com.example.nevada26.agendaapp.Modelo;

/**
 * Created by Luis Lavado on 11/05/2016.
 */
public class Operador {
    private String id_operador;
    private String operadora_nombre;
    private String estado;

    public Operador() {
    }

    public Operador(String id_operador, String operador_nombre, String estado) {
        this.setId_operador(id_operador);
        this.setOperadora_nombre(operador_nombre);
        this.setEstado(estado);
    }

    public String getId_operador() {
        return id_operador;
    }

    public void setId_operador(String id_operador) {
        this.id_operador = id_operador;
    }

    public String getOperador_nombre() {
        return getOperadora_nombre();
    }

    public void setOperador_nombre(String operador_nombre) {
        this.setOperadora_nombre(operador_nombre);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getOperadora_nombre() {
        return operadora_nombre;
    }

    public void setOperadora_nombre(String operadora_nombre) {
        this.operadora_nombre = operadora_nombre;
    }
}
