package com.examen.examen.Services.Espacio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.examen.DTO.EspacioDTO;
import com.examen.examen.Entities.Espacio;
import com.examen.examen.Exceptions.Espacio.EspacioNoEncontradoException;
import com.examen.examen.Repositories.EspacioRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspacioServiceImpl implements EspacioService {

    private final EspacioRepositorio espacioRepositorio;

    @Override
    public List<EspacioDTO> obtenerEspacios() {
        return espacioRepositorio.findAll().stream()
                .map(this::convertirAEspacioDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EspacioDTO nuevoEspacio(EspacioDTO espacioNuevo) {
        if (espacioNuevo.getNombre() == null || espacioNuevo.getTipo() == null) {
            throw new IllegalArgumentException("Nombre y tipo son obligatorios.");
        }
        
        Espacio espacio = Espacio.builder()
                .nombre(espacioNuevo.getNombre())
                .tipo(espacioNuevo.getTipo())
                .capacidadMaxima(espacioNuevo.getCapacidadMaxima())
                .disponibilidad(espacioNuevo.getDisponibilidad())
                .build();

        return convertirAEspacioDTO(espacioRepositorio.save(espacio));
    }

    @Override
    public EspacioDTO obtenerEspacio(Long id) {
        Espacio espacio = espacioRepositorio.findById(id)
                .orElseThrow(() -> new EspacioNoEncontradoException("Espacio con ID: " + id + " no existe"));
        return convertirAEspacioDTO(espacio);
    }

    @Override
    @Transactional
    public EspacioDTO actualizarEspacio(Long id, EspacioDTO espacioActualizado) {
        Espacio espacio = espacioRepositorio.findById(id)
                .orElseThrow(() -> new EspacioNoEncontradoException("Espacio con ID: " + id + " no existe"));

        if (espacioActualizado.getNombre() != null) {
            espacio.setNombre(espacioActualizado.getNombre());
        }
        if (espacioActualizado.getTipo() != null) {
            espacio.setTipo(espacioActualizado.getTipo());
        }
        if (espacioActualizado.getCapacidadMaxima() != null) {
            espacio.setCapacidadMaxima(espacioActualizado.getCapacidadMaxima());
        }
        if (espacioActualizado.getDisponibilidad() != null) {
            espacio.setDisponibilidad(espacioActualizado.getDisponibilidad());
        }

        return convertirAEspacioDTO(espacioRepositorio.save(espacio));
    }

    @Override
    @Transactional
    public void eliminarEspacio(Long id) {
        if (!espacioRepositorio.existsById(id)) {
            throw new EspacioNoEncontradoException("Espacio con ID: " + id + " no existe");
        }
        espacioRepositorio.deleteById(id);
    }

    @Override
    public List<EspacioDTO> obtenerEspacioDisponible(String disponibilidad) {
        return espacioRepositorio.otenerEspaciosPorTDisponibilidad(disponibilidad).stream()
                .map(this::convertirAEspacioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EspacioDTO> obtenerEspacioTipo(String tipoEspacio) {
        return espacioRepositorio.obtenerEspaciosPorTipo(tipoEspacio).stream()
                .map(this::convertirAEspacioDTO)
                .collect(Collectors.toList());
    }

    private EspacioDTO convertirAEspacioDTO(Espacio espacio) {
        return EspacioDTO.builder()
                .id(espacio.getId())
                .nombre(espacio.getNombre())
                .tipo(espacio.getTipo())
                .capacidadMaxima(espacio.getCapacidadMaxima())
                .disponibilidad(espacio.getDisponibilidad())
                .build();
    }
}
