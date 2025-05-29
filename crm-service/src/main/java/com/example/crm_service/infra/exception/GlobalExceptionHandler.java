package com.example.crm_service.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoExcpetion.class)
    public ResponseEntity<ErrorResponseDTO> handleClienteNaoEncontrado(ClienteNaoEncontradoExcpetion ex) {
        var erro = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DeletarClienteAtivoException.class)
    public ResponseEntity<ErrorResponseDTO> handlerDeletarClienteAtivo(DeletarClienteAtivoException ex){
        var erro = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(NomeOuRazaoSocialExistenteException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNomeOuRazaoSocialExistente(NomeOuRazaoSocialExistenteException ex){
        var erro = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(EmailExistenteException.class)
    public ResponseEntity<ErrorResponseDTO> handlerEmailExistente(EmailExistenteException ex){
        var erro = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(DocumentoExistenteException.class)
    public ResponseEntity<ErrorResponseDTO> handlerDocumentoExistente(DocumentoExistenteException ex){
        var erro = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    private record ErrorResponseDTO(
            int status_code,
            String status,
            String message,
            LocalDateTime timestamp
    ) {}
}
