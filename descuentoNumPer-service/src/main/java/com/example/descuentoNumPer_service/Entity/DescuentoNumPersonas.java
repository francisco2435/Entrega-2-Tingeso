package com.example.descuentoNumPer_service.Entity;

import javax.persistence.*;

import java.util.List;

@Entity
public class DescuentoNumPersonas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Double> descuentos;

    private int numPersonas;

    private Long idReserva;

    public DescuentoNumPersonas() {
    }

    public DescuentoNumPersonas(Long idReserva, int numPersonas, List<Double> descuentos) {
        this.descuentos = descuentos;
        this.numPersonas = numPersonas;
        this.idReserva = idReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Double> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<Double> descuentos) {
        this.descuentos = descuentos;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }
}