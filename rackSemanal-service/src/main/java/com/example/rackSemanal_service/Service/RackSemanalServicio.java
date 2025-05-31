package com.example.rackSemanal_service.Service;

import com.example.rackSemanal_service.Entity.RackSemanal;
import com.example.rackSemanal_service.Model.Reserva;
import com.example.rackSemanal_service.Repository.RackSemanalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RackSemanalServicio {
    RackSemanalRepositorio rackSemanalRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    public List<Reserva> obtenerTodasLasReservas() {
        ResponseEntity<List<Reserva>> response = restTemplate.exchange(
                "http://reserva-service/reserva/obtenerReservas",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Reserva>>() {
                }
        );
        return response.getBody();
    }

    public List<Reserva> obtenerRackSemanal() {
        List<Reserva> reservas = obtenerTodasLasReservas();
        return reservas;
    }
}
