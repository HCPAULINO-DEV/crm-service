package com.example.crm_service.infra.validation;

import com.example.crm_service.dto.CadastrarClienteDto;
import com.example.crm_service.infra.exception.EmailExistenteException;
import com.example.crm_service.repository.ClienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarEmailExistente implements ValidadorCadastroCliente{

    private final ClienteRepository clienteRepository;

    public ValidarEmailExistente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void validar(CadastrarClienteDto dto) {
        if (clienteRepository.existsByEmail(dto.email())){
            throw new EmailExistenteException("ERRO: Email já existente");
        }
    }
}
