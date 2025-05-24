package com.example.descuentoClienFrec_service.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    //Atributos para hacer reserva
    private String rutCliente;
    private String nombreCliente;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int tiempoTotal; // en minutos
    private List<String> rutsAmigos;

    // Atributos para hacer comprobante
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id autoincremental
    private Long id;

    private LocalDate fechaReserva;
    private String TipoTarifa;
    private LocalTime horaReserva;
    private int numVueltas;
    private int tiempoMax; // en minutos
    private int cantidadPersonas;

    private List<String> nombres;
    // tarifa aplicada es igual para todos
    private List<String> nombreDescuentoTamanoGrupo;
    private List<Double> valorDescuentoTamanoGrupo;
    private List<String> nombreDescuentoEspeciales; //por ser cliente frecuente o promociones especiales
    private List<Double> valorDescuentoEspeciales;
    private double montoTotal;
    private double valorIva;
    private double montoTotalConIva;
}
