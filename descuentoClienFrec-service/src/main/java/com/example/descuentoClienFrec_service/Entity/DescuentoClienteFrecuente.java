package com.example.descuentoClienFrec_service.Entity;

import javax.persistence.*;

import java.util.List;

@Entity
public class DescuentoClienteFrecuente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Double> descuentos;

    private Long idReserva;

    public DescuentoClienteFrecuente() {
    }

    public DescuentoClienteFrecuente(Long idReserva, List<Double> descuentos) {
        this.descuentos = descuentos;
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

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }
}
