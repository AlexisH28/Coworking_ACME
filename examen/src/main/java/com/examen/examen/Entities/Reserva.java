package com.examen.examen.Entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.examen.examen.Enums.estado_reserva;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reserva")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate reserva;

    @Column(nullable = false)
    private LocalTime inicio;

    @Column(nullable = false)
    private LocalTime fin;

    @Enumerated(EnumType.STRING)
    private estado_reserva estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espacio_id")
    private Espacio espacio;

    public Reserva(LocalDate reserva, LocalTime inicio, LocalTime fin, estado_reserva estado, Espacio espacio) {
        this.reserva = reserva;
        this.inicio = inicio;
        this.fin = fin;
        this.estado = estado;
        this.espacio = espacio;
    }

    @ManyToMany(mappedBy = "reserva_compartida")
    private Set<Espacio> espacio_compartido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReserva() {
        return reserva;
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

    public estado_reserva getEstado() {
        return estado;
    }

    public void setEstado(estado_reserva estado) {
        this.estado = estado;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Set<Espacio> getEspacio_compartido() {
        return espacio_compartido;
    }

    public void setEspacio_compartido(Set<Espacio> espacio_compartido) {
        this.espacio_compartido = espacio_compartido;
    }

}
