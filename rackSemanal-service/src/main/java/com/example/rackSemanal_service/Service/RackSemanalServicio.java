package com.example.rackSemanal_service.Service;

import com.example.rackSemanal_service.Entity.RackSemanal;
import com.example.rackSemanal_service.Model.Reserva;
import com.example.rackSemanal_service.Repository.RackSemanalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RackSemanalServicio {
    RackSemanalRepositorio rackSemanalRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    public List<Reserva> obtenerTodasLasReservas() {
        List<Reserva> reservas = restTemplate.getForObject("http://reserva-service/reserva/obtenerTodasLasReservas/", List.class);
        return reservas;
    }

    public List<Reserva> obtenerRackSemanal() {
        List<Reserva> reservas = obtenerTodasLasReservas();
        return reservas;
    }
}
