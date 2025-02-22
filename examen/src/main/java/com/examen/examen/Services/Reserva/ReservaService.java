package com.examen.examen.Services.Reserva;

import java.time.LocalDate;
import java.util.List;

import com.examen.examen.DTO.ReservaDTO;
import com.examen.examen.Entities.Reserva;

public interface ReservaService {

    List<ReservaDTO> obtenerReservas();

    Reserva nuevaReserva(ReservaDTO nuevaReserva, Long espacio_id);

    ReservaDTO obtenerReserva(Long id);

    boolean actualizarReserva(Long id, Long espacio_id, ReservaDTO reservaActualizada);

    void eliminarReserva(Long id);

    List<ReservaDTO> obtenerReservasEstado(String estado);

    List<ReservaDTO> obtenerReservasFecha(LocalDate fecha);

}
