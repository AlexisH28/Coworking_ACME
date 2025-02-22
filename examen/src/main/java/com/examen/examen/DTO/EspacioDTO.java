package com.examen.examen.DTO;

import com.examen.examen.Enums.disponibilidad_espacio;
import com.examen.examen.Enums.tipo_espacio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EspacioDTO {

    private Long id;
    private String nombre;
    private tipo_espacio tipo;
    private Long capacidadMaxima;
    private disponibilidad_espacio disponibilidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public tipo_espacio getTipo() {
        return tipo;
    }

    public void setTipo(tipo_espacio tipo) {
        this.tipo = tipo;
    }

    public Long getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Long capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public disponibilidad_espacio getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(disponibilidad_espacio disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
