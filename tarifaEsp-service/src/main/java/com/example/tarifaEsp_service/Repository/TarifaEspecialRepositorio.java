package com.example.tarifaEsp_service.Repository;

import com.example.tarifaEsp_service.Entity.TarifaEspecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaEspecialRepositorio extends JpaRepository<TarifaEspecial, Long> {
}
