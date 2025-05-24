package com.example.descuentoClienFrec_service.Repository;

import com.example.descuentoClienFrec_service.Entity.DescuentoClienteFrecuente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescuentoClienteFrecuenteRepositorio extends JpaRepository<DescuentoClienteFrecuente, Long> {
}
