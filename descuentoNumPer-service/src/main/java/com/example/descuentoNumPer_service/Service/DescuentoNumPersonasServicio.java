package com.example.descuentoNumPer_service.Service;

import com.example.descuentoNumPer_service.Entity.DescuentoNumPersonas;
import com.example.descuentoNumPer_service.Model.Usuario;
import com.example.descuentoNumPer_service.Repository.DescuentoNumPersonasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DescuentoNumPersonasServicio {
    @Autowired
    private DescuentoNumPersonasRepositorio descuentoNumPersonasRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    public List<Double> aplicarDescuentosNumPersona(Long idReserva, int numPersonas) {
        double descuento = 0.0;

        if (numPersonas >= 3 && numPersonas <= 5) {
            descuento = 0.10;
        } else if (numPersonas >= 6 && numPersonas <= 10) {
            descuento = 0.20;
        } else if (numPersonas > 10 && numPersonas <= 15) {
            descuento = 0.30;
        }

        List<Double> descuentos = new ArrayList<>();
        for(int i = 0; i < numPersonas; i++){
            descuentos.add(descuento);
        }

        DescuentoNumPersonas descuentoNumPersonas = new DescuentoNumPersonas(idReserva, numPersonas, descuentos);

        //descuentoNumPersonasRepositorio.save(descuentoNumPersonas);
        return descuentos;
    }
}
