package com.example.crm_service.dto;

import com.example.crm_service.entity.Endereco;
import com.example.crm_service.entity.Status;
import com.example.crm_service.entity.TipoDocumento;

public record AtualizarClienteDto(
        String nome,
        TipoDocumento tipoDocumento,
        String documento,
        String email,
        String telefone,
        Endereco endereco,
        Status status
) {
}
