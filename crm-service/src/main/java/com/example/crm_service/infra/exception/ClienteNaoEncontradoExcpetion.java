package com.example.crm_service.infra.exception;

public class ClienteNaoEncontradoExcpetion extends RuntimeException{
    public ClienteNaoEncontradoExcpetion(String mensagem){
        super(mensagem);
    }
}
