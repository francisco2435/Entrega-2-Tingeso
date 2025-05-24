package com.example.tarifa_service.Repository;


import com.example.tarifa_service.Entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepositorio extends JpaRepository<Tarifa, Long> {
    Tarifa findByTipoAndTiempoMax(String tipo, int tiempomax);
    Tarifa findByTipoAndNumeroVueltas(String tipo, int numeroVueltas);
}
