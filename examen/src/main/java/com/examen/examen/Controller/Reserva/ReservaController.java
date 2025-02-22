package com.examen.examen.Controller.Reserva;

import com.examen.examen.DTO.ReservaDTO;
import com.examen.examen.Entities.Reserva;
import com.examen.examen.Services.Reserva.ReservaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerReservas() {
        List<ReservaDTO> reservas = reservaService.obtenerReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obtenerReserva(@PathVariable Long id) {
        ReservaDTO reserva = reservaService.obtenerReserva(id);
        return ResponseEntity.ok(reserva);
    }

    @PostMapping("/{espacio_id}")
    public ResponseEntity<Reserva> nuevaReserva(@RequestBody ReservaDTO reservaDTO, @PathVariable Long espacio_id) {
        Reserva reserva = reservaService.nuevaReserva(reservaDTO, espacio_id);
        return ResponseEntity.ok(reserva);
    }

    @PutMapping("/{id}/{espacio_id}")
    public ResponseEntity<Boolean> actualizarReserva(@PathVariable Long id, 
                                                     @PathVariable Long espacio_id, 
                                                     @RequestBody ReservaDTO reservaActualizada) {
        boolean actualizado = reservaService.actualizarReserva(id, espacio_id, reservaActualizada);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorEstado(@PathVariable String estado) {
        List<ReservaDTO> reservas = reservaService.obtenerReservasEstado(estado);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorFecha(@PathVariable LocalDate fecha) {
        List<ReservaDTO> reservas = reservaService.obtenerReservasFecha(fecha);
        return ResponseEntity.ok(reservas);
    }
}
