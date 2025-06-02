package com.example.tarifaEsp_service.Controller;

import com.example.tarifaEsp_service.Entity.TarifaEspecial;
import com.example.tarifaEsp_service.Service.TarifaEspecialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarifaEsp")
public class TarifaEspecialControlador {
    @Autowired
    private TarifaEspecialServicio tarifaEspecialServicio;

    @PostMapping("/nuevaTarifaEspecial")
    public ResponseEntity<TarifaEspecial> nuevaTarifa(@RequestBody TarifaEspecial tarifa) {
        return ResponseEntity.ok(tarifaEspecialServicio.NuevaTarifa(tarifa.getNumeroVueltas(), tarifa.getTiempoMax(), tarifa.getPrecio(), tarifa.getDuracionReserva(), tarifa.getTipo()));
    }

    @GetMapping("/obtenerTarifas")
    public ResponseEntity<List<TarifaEspecial>> obtenerTarifas() {
        return ResponseEntity.ok(tarifaEspecialServicio.ObtenerTodasLasTarifas());
    }

    @PutMapping("/modificarTarifa")
    public void modificarTarifa(@RequestBody TarifaEspecial tarifa) {
        tarifaEspecialServicio.modificarTarifa(tarifa.getId(), tarifa.getNumeroVueltas(), tarifa.getTiempoMax(), tarifa.getPrecio(), tarifa.getDuracionReserva(), tarifa.getTipo());
    }

    @GetMapping("/obtenerTarifa")
    public ResponseEntity<TarifaEspecial> obtenerTarifa(@RequestParam Long id) {
        return ResponseEntity.ok(tarifaEspecialServicio.obtenerTarifa(id));
    }

    @GetMapping("/obtenerDescuentosCumpleaneos")
    public ResponseEntity<List<Double>> hacerDescuentoCumpleanios(
            @RequestParam List<String> rutsIntegrantes,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaReserva) {

        return ResponseEntity.ok(
                tarifaEspecialServicio.obtenerDescuentoCumpleanios(rutsIntegrantes, fechaReserva)
        );
    }

    @GetMapping("/obtenerTipoTarifaPorFecha")
    public ResponseEntity<String> obtenerTipoTarifaPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaReserva) {
        return ResponseEntity.ok(tarifaEspecialServicio.obtenerTipoTarifaPorFecha(fechaReserva));
    }

    @GetMapping("/obtenerTarifaPorTipo")
    public ResponseEntity<List<TarifaEspecial>> obtenerTarifaPorTipo(@RequestParam String tipo) {
        System.out.println("Tipo recibido: " + tipo);
        return ResponseEntity.ok(tarifaEspecialServicio.obtenerTarifaPorTipo(tipo));
    }
}
