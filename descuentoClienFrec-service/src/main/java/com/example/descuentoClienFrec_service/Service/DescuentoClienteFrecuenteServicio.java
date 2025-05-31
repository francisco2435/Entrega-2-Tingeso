package com.example.descuentoClienFrec_service.Service;

import com.example.descuentoClienFrec_service.Entity.DescuentoClienteFrecuente;
import com.example.descuentoClienFrec_service.Model.Reserva;
import com.example.descuentoClienFrec_service.Model.Usuario;
import com.example.descuentoClienFrec_service.Repository.DescuentoClienteFrecuenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class DescuentoClienteFrecuenteServicio {
    @Autowired
    private DescuentoClienteFrecuenteRepositorio descuentoClienteFrecuenteRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    public List<Usuario> obtenerUsuariosPorRut(List<String> rutsIntegrantes) {
        Usuario usuario;
        List<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < rutsIntegrantes.size(); i++) {
            usuario = restTemplate.getForObject("http://usuario-service/usuario/buscarPorRut/"+rutsIntegrantes.get(i), Usuario.class);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public List<Reserva> obtenerReservasEntre(LocalDate inicioMes, LocalDate finMes) {
        String url = "http://reserva-service/reserva/obtenerReservasEntreFechas?fechaInicio={inicioMes}&fechaFin={finMes}";

        ResponseEntity<List<Reserva>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Reserva>>() {},
                inicioMes.toString(),
                finMes.toString()
        );

        return response.getBody();
    }

    public List<Double> obtenerDescuentoFrecuencia(Long idReserva, List<String> rutsIntegrantes, LocalDate fechaReserva){
        List<Double> descuentos = new ArrayList<>();

        for (int i = 0; i < rutsIntegrantes.size(); i++) {
            // Descuento por frecuencia del cliente
            int frecuencia = calcularFrecuencia(fechaReserva, rutsIntegrantes.get(i));
            if (frecuencia >= 2 && frecuencia <= 4) {
                descuentos.add(0.10);
            } else if (frecuencia >= 5 && frecuencia <= 6) {
                descuentos.add(0.20);
            } else if (frecuencia > 6) {
                descuentos.add(0.30);
            } else {
                descuentos.add(0.00);
            }
        }

        DescuentoClienteFrecuente descuentoClienteFrecuente = new DescuentoClienteFrecuente(idReserva, descuentos);

        //descuentoClienteFrecuenteRepositorio.save(descuentoClienteFrecuente);

        return descuentos;
    }

    public int calcularFrecuencia(LocalDate fecha, String rutIntegrante) {
        // Verificar si el rutIntegrante es null o vacío y lanzar excepción
        if (rutIntegrante == null || rutIntegrante.isEmpty()) {
            throw new IllegalArgumentException("El rut no puede ser vacío");
        }

        // Obtener las reservas realizadas en el mes
        YearMonth mesIngresado = YearMonth.from(fecha);
        LocalDate inicioMes = mesIngresado.atDay(1);
        LocalDate finMes = mesIngresado.atEndOfMonth();
        int frecuenciaReservasRealizadas = 0;  // Cuantas veces aparece su rut en las reservas del mes

        // Obtener las reservas del mes
        List<Reserva> reservasDelMes = obtenerReservasEntre(inicioMes, finMes);

        for (Reserva reserva : reservasDelMes) {
            // Si el rutIntegrante es el cliente que hace la reserva o está en la lista de amigos
            if (reserva.getRutCliente().equals(rutIntegrante) ||
                    (reserva.getRutsAmigos() != null && reserva.getRutsAmigos().contains(rutIntegrante))) {
                frecuenciaReservasRealizadas++;
            }
        }

        return frecuenciaReservasRealizadas;
    }

}
