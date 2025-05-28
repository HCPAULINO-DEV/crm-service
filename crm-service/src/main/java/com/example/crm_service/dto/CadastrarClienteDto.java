package com.example.crm_service.dto;

import com.example.crm_service.entity.Endereco;
import com.example.crm_service.entity.TipoDocumento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarClienteDto(

        @NotBlank
        String nome,

        @NotNull
        TipoDocumento tipoDocumento,

        @NotBlank
        String documento,

        @Email
        String email,

        @NotBlank
        String telefone,

        @NotNull
        Endereco endereco
) {
}
