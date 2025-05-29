package com.example.crm_service.infra.exception;

public class DeletarClienteAtivoException extends RuntimeException{
    public DeletarClienteAtivoException(String mensagem){
        super(mensagem);
    }
}
