package com.example.reserva_service.Repository;

import com.example.reserva_service.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    List<Reserva> findByFechaReserva(LocalDate fecha);

    List<Reserva> findByFechaReservaBetween(LocalDate startDate, LocalDate endDate);

}
