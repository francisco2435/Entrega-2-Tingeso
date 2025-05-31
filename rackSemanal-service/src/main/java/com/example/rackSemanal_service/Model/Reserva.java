package com.example.rackSemanal_service.Model;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Reserva {
    private List<String> rutsAmigos;

    // Atributos para hacer comprobante
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id autoincremental
    private Long id;

    private LocalDate fechaReserva;
    private String tipoTarifa;
    private LocalTime horaReserva;
    private int numVueltas;
    private int tiempoMax; // en minutos
    private int cantidadPersonas;
    private String correoCliente;
    private int duracionReserva; // en minutos

    @ElementCollection
    private List<String> nombres;
    private double precioTarifa; // precio de la tarifa seleccionada

    @ElementCollection
    private List<String> tiposDescuentos; // nombres de los descuentos aplicados
    @ElementCollection
    private List<Double> descuentos;
    @ElementCollection
    private List<Double> montosConDescuento;

    private double montoTotal;
    private double valorIva;
    private double montoTotalConIva;

    public List<String> getRutsAmigos() {
        return rutsAmigos;
    }

    public void setRutsAmigos(List<String> rutsAmigos) {
        this.rutsAmigos = rutsAmigos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(String tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public LocalTime getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(LocalTime horaReserva) {
        this.horaReserva = horaReserva;
    }

    public int getNumVueltas() {
        return numVueltas;
    }

    public void setNumVueltas(int numVueltas) {
        this.numVueltas = numVueltas;
    }

    public int getTiempoMax() {
        return tiempoMax;
    }

    public void setTiempoMax(int tiempoMax) {
        this.tiempoMax = tiempoMax;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public int getDuracionReserva() {
        return duracionReserva;
    }

    public void setDuracionReserva(int duracionReserva) {
        this.duracionReserva = duracionReserva;
    }

    public List<String> getNombres() {
        return nombres;
    }

    public void setNombres(List<String> nombres) {
        this.nombres = nombres;
    }

    public double getPrecioTarifa() {
        return precioTarifa;
    }

    public void setPrecioTarifa(double precioTarifa) {
        this.precioTarifa = precioTarifa;
    }

    public List<String> getTiposDescuentos() {
        return tiposDescuentos;
    }

    public void setTiposDescuentos(List<String> tiposDescuentos) {
        this.tiposDescuentos = tiposDescuentos;
    }

    public List<Double> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<Double> descuentos) {
        this.descuentos = descuentos;
    }

    public List<Double> getMontosConDescuento() {
        return montosConDescuento;
    }

    public void setMontosConDescuento(List<Double> montosConDescuento) {
        this.montosConDescuento = montosConDescuento;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getValorIva() {
        return valorIva;
    }

    public void setValorIva(double valorIva) {
        this.valorIva = valorIva;
    }

    public double getMontoTotalConIva() {
        return montoTotalConIva;
    }

    public void setMontoTotalConIva(double montoTotalConIva) {
        this.montoTotalConIva = montoTotalConIva;
    }
}
