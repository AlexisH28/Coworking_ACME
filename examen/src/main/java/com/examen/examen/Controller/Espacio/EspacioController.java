package com.examen.examen.Controller.Espacio;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examen.examen.DTO.EspacioDTO;
import com.examen.examen.Services.Espacio.EspacioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/espacios")
@RequiredArgsConstructor
public class EspacioController {

    private final EspacioService espacioService;

    @GetMapping
    public ResponseEntity<List<EspacioDTO>> obtenerEspacios() {
        return ResponseEntity.ok(espacioService.obtenerEspacios());
    }

    @PostMapping
    public ResponseEntity<EspacioDTO> crearEspacio(@RequestBody EspacioDTO espacioNuevo) {
        return ResponseEntity.ok(espacioService.nuevoEspacio(espacioNuevo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspacioDTO> obtenerEspacio(@PathVariable Long id) {
        return ResponseEntity.ok(espacioService.obtenerEspacio(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspacioDTO> actualizarEspacio(@PathVariable Long id, @RequestBody EspacioDTO espacioActualizado) {
        return ResponseEntity.ok(espacioService.actualizarEspacio(id, espacioActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEspacio(@PathVariable Long id) {
        espacioService.eliminarEspacio(id);
        return ResponseEntity.ok("Espacio eliminado correctamente.");
    }

    @GetMapping("/disponibilidad/{disponibilidad}")
    public ResponseEntity<List<EspacioDTO>> obtenerEspaciosPorDisponibilidad(@PathVariable String disponibilidad) {
        return ResponseEntity.ok(espacioService.obtenerEspacioDisponible(disponibilidad));
    }

    @GetMapping("/tipo/{tipoEspacio}")
    public ResponseEntity<List<EspacioDTO>> obtenerEspaciosPorTipo(@PathVariable String tipoEspacio) {
        return ResponseEntity.ok(espacioService.obtenerEspacioTipo(tipoEspacio));
    }
}
