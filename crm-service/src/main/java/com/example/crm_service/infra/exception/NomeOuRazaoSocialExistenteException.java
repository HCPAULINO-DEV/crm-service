package com.example.crm_service.infra.exception;

public class NomeOuRazaoSocialExistenteException extends RuntimeException{
    public  NomeOuRazaoSocialExistenteException(String mensagem){
        super(mensagem);
    }
}
