package com.examen.examen.Entities;

import java.util.List;
import java.util.Set;

import com.examen.examen.Enums.disponibilidad_espacio;
import com.examen.examen.Enums.tipo_espacio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "espacio")
@Builder
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private tipo_espacio tipo;

    @Column(name = "capacidad_maxima", nullable = false)
    private Long capacidadMaxima;

    @Enumerated(EnumType.STRING)
    private disponibilidad_espacio disponibilidad;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "espacio")
    private List<Reserva> reservas;

    @ManyToMany
    @JoinTable(name = "espacio_compartido", joinColumns = @JoinColumn(name = "reserva_id"), inverseJoinColumns = @JoinColumn(name = "espacio_id"))
    private Set<Reserva> reserva_compartida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<Reserva> getReserva_compartida() {
        return reserva_compartida;
    }

    public void setReserva_compartida(Set<Reserva> reserva_compartida) {
        this.reserva_compartida = reserva_compartida;
    }

}
