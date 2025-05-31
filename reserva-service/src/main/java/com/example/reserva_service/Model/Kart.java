package com.example.reserva_service.Model;

public class Kart {

    private Long id;
    private String codigo;
    private String modelo;
    private String estado;

    //Constructores
    public Kart() {
    }

    public Kart(String codigo, String modelo, String estado) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.estado = estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCodigo() {
        return codigo;
    }
}
