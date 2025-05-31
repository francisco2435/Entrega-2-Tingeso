package com.example.reserva_service.Controller;

import com.example.reserva_service.Entity.Reserva;
import com.example.reserva_service.Service.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/reserva")
public class ReservaControlador {
    @Autowired
    ReservaServicio reservaServicio;

    @PostMapping("/hacerReserva")
    public ResponseEntity<Reserva> hacerReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaServicio.realizarReserva(
                reserva.getRutCliente(),
                reserva.getNombreCliente(),
                reserva.getCorreoCliente(),
                reserva.getFechaReserva(),
                reserva.getHoraInicio(),
                reserva.getRutsAmigos(),
                reserva.getNombres(),
                reserva.getNumVueltas(),
                reserva.getTiempoMax(),
                reserva.getPrecioTarifa(),
                reserva.getDuracionReserva(),
                reserva.getTipoTarifa()
        ));
    }

    @GetMapping("/obtenerReservas")
    public ResponseEntity<List<Reserva>> obtenerReservas() {
        return ResponseEntity.ok(reservaServicio.ObtenerReservas());
    }

    @GetMapping("/obtenerReservasEntreFechas")
    public ResponseEntity<List<Reserva>> obtenerReservasEntreFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        return ResponseEntity.ok(reservaServicio.obtenerReservasEntreFechas(fechaInicio, fechaFin));
    }

}
