package com.example.crm_service.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Endereco(

        String cep,

        @NotBlank
        String estado,

        @NotBlank
        String cidade,

        @NotBlank
        String bairro,

        @NotBlank
        String rua,

        @NotBlank
        String numero
) {
}
