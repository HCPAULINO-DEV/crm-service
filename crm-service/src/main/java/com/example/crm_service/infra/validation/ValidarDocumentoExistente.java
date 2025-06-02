package com.example.crm_service.infra.validation;

import com.example.crm_service.dto.CadastrarClienteDto;
import com.example.crm_service.infra.exception.DocumentoExistenteException;
import com.example.crm_service.repository.ClienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarDocumentoExistente implements ValidadorCadastroCliente{

    private final ClienteRepository clienteRepository;

    public ValidarDocumentoExistente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void validar(CadastrarClienteDto dto) {
        if (clienteRepository.existsByDocumento(dto.documento())){
            throw new DocumentoExistenteException("ERRO: Documento já existente");
        }
    }
}
