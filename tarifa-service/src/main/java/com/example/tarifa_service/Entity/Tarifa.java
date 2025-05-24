package com.example.tarifa_service.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


@Entity
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id autoincremental
    private Long id;

    private int numeroVueltas;
    private int tiempoMax;
    private double precio;
    private int duracionReserva;
    private String tipo;

    public Tarifa() {
    }

    public Tarifa(int numeroVueltas, int tiempoMax, Double precio, int duracionReserva, String tipo) {
        this.numeroVueltas = numeroVueltas;
        this.tiempoMax = tiempoMax;
        this.precio = precio;
        this.duracionReserva = duracionReserva;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public int getNumeroVueltas() {
        return numeroVueltas;
    }

    public int getTiempoMax() {
        return tiempoMax;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDuracionReserva() {
        return duracionReserva;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNumeroVueltas(int numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    public void setTiempoMax(int tiempoMax) {
        this.tiempoMax = tiempoMax;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setDuracionReserva(int duracionReserva) {
        this.duracionReserva = duracionReserva;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}