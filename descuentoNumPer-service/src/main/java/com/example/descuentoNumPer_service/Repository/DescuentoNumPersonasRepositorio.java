package com.example.descuentoNumPer_service.Repository;

import com.example.descuentoNumPer_service.Entity.DescuentoNumPersonas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescuentoNumPersonasRepositorio extends JpaRepository<DescuentoNumPersonas, Long> {

}
