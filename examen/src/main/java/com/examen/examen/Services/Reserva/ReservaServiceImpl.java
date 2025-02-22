package com.examen.examen.Services.Reserva;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.examen.examen.DTO.ReservaDTO;
import com.examen.examen.Entities.Espacio;
import com.examen.examen.Entities.Reserva;
import com.examen.examen.Enums.estado_reserva;
import com.examen.examen.Enums.tipo_espacio;
import com.examen.examen.Exceptions.Espacio.EspacioNoEncontradoException;
import com.examen.examen.Exceptions.Reserva.ReservaNoEncontradaException;
import com.examen.examen.Repositories.EspacioRepositorio;
import com.examen.examen.Repositories.ReservaRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepositorio reservaRepositorio;
    private final EspacioRepositorio espacioRepositorio;

    @Override
    public List<ReservaDTO> obtenerReservas() {
        List<Reserva> reservas = reservaRepositorio.findAll();
        return reservas.stream()
                .map(reserva -> ReservaDTO.builder()
                        .id(reserva.getId())
                        .reserva(reserva.getReserva())
                        .inicio(reserva.getInicio())
                        .fin(reserva.getFin())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Reserva nuevaReserva(ReservaDTO nuevaReserva, Long espacio_id) {

        Espacio espacio = espacioRepositorio.findById(espacio_id)
                .orElseThrow(() -> new EspacioNoEncontradoException("No se ha encontrado el espacio"));

        Reserva reservaNueva = new Reserva(nuevaReserva.getReserva(), nuevaReserva.getInicio(), nuevaReserva.getFin(),
                estado_reserva.PENDIENTE, espacio);

        if (espacio.getTipo().equals(tipo_espacio.ESCRITORIO_COMPARTIDO))
            reservaNueva.getEspacio_compartido().add(espacio);

        reservaRepositorio.save(reservaNueva);

        return reservaNueva;

    }

    @Override
    public ReservaDTO obtenerReserva(Long id) {
        Reserva reserva = reservaRepositorio.findById(id)
                .orElseThrow(() -> new ReservaNoEncontradaException("No se ha encontrado la reserva " + id));
        return ReservaDTO.builder()
                .id(id)
                .reserva(reserva.getReserva())
                .inicio(reserva.getInicio())
                .fin(reserva.getFin())
                .estado(reserva.getEstado())
                .build();
    }

    @Override
    public boolean actualizarReserva(Long id, Long espacio_id, ReservaDTO reservaActualizada) {

        Espacio espacio = espacioRepositorio.findById(espacio_id)
                .orElseThrow(() -> new EspacioNoEncontradoException("Espacio no encontrado"));

        int filasEncontradas = reservaRepositorio.actualizarReserva(
                id,
                reservaActualizada.getReserva(),
                reservaActualizada.getInicio(),
                reservaActualizada.getFin(),
                reservaActualizada.getEstado(),
                espacio.getId());

        return filasEncontradas > 0;

    }

    @Override
    public void eliminarReserva(Long id) {
        Reserva reserva = reservaRepositorio.findById(id)
                .orElseThrow(() -> new ReservaNoEncontradaException("Reserva no encontrada"));

        reserva.getEspacio_compartido().clear();

        reservaRepositorio.delete(reserva);

    }

    @Override
    public List<ReservaDTO> obtenerReservasEstado(String estado) {
        try {
            estado_reserva estadoEnum = estado_reserva.valueOf(estado.toUpperCase());

            List<Reserva> reservas = reservaRepositorio.findByEstado(estadoEnum);
            return reservas.stream()
                    .map(reserva -> ReservaDTO.builder()
                            .id(reserva.getId())
                            .reserva(reserva.getReserva())
                            .inicio(reserva.getInicio())
                            .fin(reserva.getFin())
                            .estado(reserva.getEstado())
                            .build())
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new ReservaNoEncontradaException("El estado proporcionado no es válido: " + estado);
        }
    }

    @Override
    public List<ReservaDTO> obtenerReservasFecha(LocalDate fecha) {
        List<Reserva> reservas = reservaRepositorio.findByFecha(fecha);
        return reservas.stream()
                .map(reserva -> ReservaDTO.builder()
                        .id(reserva.getId())
                        .reserva(reserva.getReserva())
                        .inicio(reserva.getInicio())
                        .fin(reserva.getFin())
                        .estado(reserva.getEstado())
                        .build())
                .collect(Collectors.toList());
    }

}
