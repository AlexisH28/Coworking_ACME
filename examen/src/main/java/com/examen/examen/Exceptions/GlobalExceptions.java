package com.examen.examen.Exceptions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.examen.examen.Exceptions.Espacio.EspacioNoEncontradoException;
import com.examen.examen.Exceptions.Reserva.ReservaNoEncontradaException;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(ReservaNoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> reservaNoEncontrada(ReservaNoEncontradaException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("TimeStamp", LocalDate.now());
        respuesta.put("message", e.getMessage());
        respuesta.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EspacioNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> espacionNoEncontrado(EspacioNoEncontradoException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("TimeStamp", LocalDate.now());
        respuesta.put("message", e.getMessage());
        respuesta.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> erroresGenerales(Exception e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("TimeStamp", LocalDate.now());
        respuesta.put("message", e.getMessage());
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
