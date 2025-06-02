package com.example.crm_service.infra.validation;

import com.example.crm_service.dto.CadastrarClienteDto;
import com.example.crm_service.repository.ClienteRepository;

public interface ValidadorCadastroCliente {
    void validar(CadastrarClienteDto dto);
}
