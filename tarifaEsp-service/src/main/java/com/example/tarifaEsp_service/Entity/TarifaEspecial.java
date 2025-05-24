package com.example.tarifaEsp_service.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaEspecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id autoincremental
    private Long id;

    private int numeroVueltas;
    private int tiempoMax;
    private double precio;
    private int duracionReserva;
    private String tipo;

    public TarifaEspecial(int numeroVueltas, int tiempoMax, double precio, int duracionReserva, String tipo) {
        this.numeroVueltas = numeroVueltas;
        this.tiempoMax = tiempoMax;
        this.precio = precio;
        this.duracionReserva = duracionReserva;
        this.tipo = tipo;
    }
}
