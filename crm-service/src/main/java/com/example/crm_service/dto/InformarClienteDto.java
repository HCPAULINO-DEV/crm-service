package com.example.crm_service.dto;

import com.example.crm_service.entity.Cliente;
import com.example.crm_service.entity.Endereco;
import com.example.crm_service.entity.Status;
import com.example.crm_service.entity.TipoDocumento;

import java.util.UUID;

public record InformarClienteDto(
        String id,
        String nome,
        TipoDocumento tipoDocumento,
        String documento,
        String email,
        String telefone,
        Endereco endereco,
        Status status
) {
    public InformarClienteDto(Cliente cliente){
        this(cliente.getId().toString(), cliente.getNome(), cliente.getTipoDocumento(), cliente.getDocumento(), cliente.getEmail(), cliente.getTelefone(), cliente.getEndereco(), cliente.getStatus());
    }
}
