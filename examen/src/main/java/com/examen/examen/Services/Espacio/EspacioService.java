package com.examen.examen.Services.Espacio;

import java.util.List;

import com.examen.examen.DTO.EspacioDTO;

public interface EspacioService {

    List<EspacioDTO> obtenerEspacios();

    EspacioDTO nuevoEspacio(EspacioDTO espacioNuevo);

    EspacioDTO obtenerEspacio(Long id);

    EspacioDTO actualizarEspacio(Long id, EspacioDTO espacioActualizado);

    void eliminarEspacio(Long id);

    List<EspacioDTO> obtenerEspacioDisponible(String disponibilidad);

    List<EspacioDTO> obtenerEspacioTipo(String tipoEspacio);

}
