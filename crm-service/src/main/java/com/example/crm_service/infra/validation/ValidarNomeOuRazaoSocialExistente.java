package com.example.crm_service.infra.validation;

import com.example.crm_service.dto.CadastrarClienteDto;
import com.example.crm_service.infra.exception.NomeOuRazaoSocialExistenteException;
import com.example.crm_service.repository.ClienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarNomeOuRazaoSocialExistente implements ValidadorCadastroCliente{

    private final ClienteRepository clienteRepository;

    public ValidarNomeOuRazaoSocialExistente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void validar(CadastrarClienteDto dto) {
        if (clienteRepository.existsByNome(dto.nome())){
            throw new NomeOuRazaoSocialExistenteException("ERRO: Nome ou Razão Social já existente");
        }

    }
}
