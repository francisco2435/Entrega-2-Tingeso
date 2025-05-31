package com.example.rackSemanal_service.Controller;

import com.example.rackSemanal_service.Model.Reserva;
import com.example.rackSemanal_service.Service.RackSemanalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rackSemanal")
public class RackSemanalControlador {
    @Autowired
    RackSemanalServicio rackSemanalServicio;

    @GetMapping("/")
    public List<Reserva> obtenerRackSemanal() {
        return rackSemanalServicio.obtenerRackSemanal();
    }
}
