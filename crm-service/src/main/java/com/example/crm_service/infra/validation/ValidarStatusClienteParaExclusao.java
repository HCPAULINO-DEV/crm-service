package com.example.crm_service.infra.validation;

import com.example.crm_service.entity.Cliente;
import com.example.crm_service.entity.Status;
import com.example.crm_service.infra.exception.ClienteNaoEncontradoExcpetion;
import com.example.crm_service.infra.exception.DeletarClienteAtivoException;
import com.example.crm_service.repository.ClienteRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidarStatusClienteParaExclusao implements ValidadorDeleteCliente{

    private final ClienteRepository clienteRepository;

    public ValidarStatusClienteParaExclusao(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void validar(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoExcpetion("Cliente não foi encontrado"));

        if (cliente.getStatus().equals(Status.ATIVO)){
            throw new DeletarClienteAtivoException("Para deletar um cliente ele deve conter o status de INATIVO!");
        }

    }
}
