package com.examen.examen.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.examen.examen.Entities.Reserva;
import com.examen.examen.Enums.estado_reserva;

import jakarta.transaction.Transactional;

public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Reserva r SET " +
            "r.reserva = COALESCE(:reserva, r.reserva), " +
            "r.inicio = COALESCE(:inicio, r.inicio), " +
            "r.fin = COALESCE(:fin, r.fin), " +
            "r.estado = COALESCE(:estado, r.estado), " +
            "r.espacio = COALESCE((SELECT e FROM Espacio e WHERE e.id = :espacio_id), r.espacio) " +
            "WHERE r.id = :id")
    int actualizarReserva(Long id, LocalDate reserva, LocalTime inicio, LocalTime fin, estado_reserva estado,
            Long espacio_id);

    @Query("SELECT r FROM Reserva r WHERE r.estado = :estado")
    List<Reserva> findByEstado(estado_reserva estado);

    @Query("SELECT r FROM Reserva r WHERE r.reserva = :fecha")
    List<Reserva> findByFecha(LocalDate fecha);

}
