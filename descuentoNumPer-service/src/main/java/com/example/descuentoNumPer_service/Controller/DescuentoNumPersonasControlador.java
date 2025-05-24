package com.example.descuentoNumPer_service.Controller;

import com.example.descuentoNumPer_service.Service.DescuentoNumPersonasServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/descuentoNumPer")
public class DescuentoNumPersonasControlador {

    DescuentoNumPersonasServicio descuentoNumPersonasServicio;

    @PostMapping("/nuevoDescuento")
    public List<Double> aplicarDescuentosNumPersonas(@RequestParam Long idReserva,
                                                     @RequestParam int numPersonas){
        return descuentoNumPersonasServicio.aplicarDescuentosNumPersona(idReserva, numPersonas);
    }



}
