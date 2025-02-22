package com.examen.examen.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.examen.examen.Enums.estado_reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long id;
    private LocalDate reserva;
    private LocalTime inicio;
    private LocalTime fin;
    private estado_reserva estado;

    public LocalDate getReserva() {
        return reserva;
    }

    public estado_reserva getEstado() {
        return estado;
    }

    public void setEstado(estado_reserva estado) {
        this.estado = estado;
    }

    public void setReserva(LocalDate reserva) {
        this.reserva = reserva;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
