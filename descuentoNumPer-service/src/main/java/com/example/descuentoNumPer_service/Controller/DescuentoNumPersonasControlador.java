package com.example.descuentoNumPer_service.Controller;

import com.example.descuentoNumPer_service.Service.DescuentoNumPersonasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descuentoNumPer")
public class DescuentoNumPersonasControlador {

    @Autowired
    DescuentoNumPersonasServicio descuentoNumPersonasServicio;

    @GetMapping("/nuevoDescuento")
    public List<Double> aplicarDescuentosNumPersonas(@RequestParam Long idReserva,
                                                     @RequestParam int numPersonas){
        return descuentoNumPersonasServicio.aplicarDescuentosNumPersona(idReserva, numPersonas);
    }



}
