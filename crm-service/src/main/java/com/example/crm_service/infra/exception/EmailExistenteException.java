package com.example.crm_service.infra.exception;

public class EmailExistenteException extends RuntimeException{
    public EmailExistenteException(String mensagem){
        super(mensagem);
    }
}
