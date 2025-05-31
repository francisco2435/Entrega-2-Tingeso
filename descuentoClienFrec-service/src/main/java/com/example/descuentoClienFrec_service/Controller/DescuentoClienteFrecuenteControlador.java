package com.example.descuentoClienFrec_service.Controller;

import com.example.descuentoClienFrec_service.Entity.DescuentoClienteFrecuente;
import com.example.descuentoClienFrec_service.Service.DescuentoClienteFrecuenteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/descuentoClienteFrecuente")
public class DescuentoClienteFrecuenteControlador {
    @Autowired
    private DescuentoClienteFrecuenteServicio descuentoClienteFrecuenteServicio;

    @GetMapping("/obtenerDescuentosFrecuencia")
    public ResponseEntity<List<Double>> hacerDescuentoFrecuencia(@RequestParam Long idReserva,
                                                                 @RequestParam List<String> rutsIntegrantes,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaReserva) {
        return ResponseEntity.ok(descuentoClienteFrecuenteServicio.obtenerDescuentoFrecuencia(idReserva, rutsIntegrantes, fechaReserva));
    }
}
