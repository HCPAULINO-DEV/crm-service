package com.example.crm_service.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoExcpetion.class)
    public ResponseEntity<ErrorResponseDTO> handleClienteNaoEncontrado(ClienteNaoEncontradoExcpetion ex) {
        var erro = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    public record ErrorResponseDTO(
            int status,
            String message,
            LocalDateTime timestamp
    ) {}
}
