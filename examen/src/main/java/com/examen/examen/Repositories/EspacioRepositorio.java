package com.examen.examen.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.examen.examen.Entities.Espacio;
import com.examen.examen.Enums.disponibilidad_espacio;
import com.examen.examen.Enums.tipo_espacio;

import jakarta.transaction.Transactional;

public interface EspacioRepositorio extends JpaRepository<Espacio, Long> {

    @Query("SELECT e FROM Espacio e WHERE e.tipo = :tipo")
    List<Espacio> obtenerEspaciosPorTipo(String tipoEspacio);

    @Query("SELECT e FROM espacio e where e.disponibilidad = :disponibilidad")
    List<Espacio> otenerEspaciosPorTDisponibilidad(String disponibilidad);

    @Modifying
    @Transactional
    @Query("UPDATE Espacio e SET " +
            "e.nombre = COALESCE(:nombre, e.nombre), " +
            "e.tipo = COALESCE(:tipo, e.tipo), " +
            "e.capacidadMaxima = COALESCE(:capacidadMaxima, e.capacidadMaxima), " +
            "e.disponibilidad = COALESCE(:disponibilidad, e.disponibilidad) " +
            "WHERE e.id = :id")
    int actualizarEspacio(Long id, String nombre, tipo_espacio tipo, Long capacidadMaxima,
            disponibilidad_espacio disponibilidad);

}
