package com.example.crm_service.infra.exception;

public class DocumentoExistenteException extends RuntimeException{
    public DocumentoExistenteException(String mensagem){
        super(mensagem);
    }
}
