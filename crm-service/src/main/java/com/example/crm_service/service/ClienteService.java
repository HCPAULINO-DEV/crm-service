package com.example.crm_service.service;

import com.example.crm_service.dto.AtualizarClienteDto;
import com.example.crm_service.dto.CadastrarClienteDto;
import com.example.crm_service.dto.InformarClienteDto;
import com.example.crm_service.entity.Cliente;
import com.example.crm_service.entity.Status;
import com.example.crm_service.infra.exception.*;
import com.example.crm_service.infra.validation.ValidadorCadastroCliente;
import com.example.crm_service.infra.validation.ValidadorDeleteCliente;
import com.example.crm_service.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final List<ValidadorCadastroCliente> validadoresCadastro;
    private final List<ValidadorDeleteCliente> validadoresDelete;

    public ClienteService(ClienteRepository clienteRepository, List<ValidadorCadastroCliente> validadoresCadastro, List<ValidadorDeleteCliente> validadoresDelete) {
        this.clienteRepository = clienteRepository;
        this.validadoresCadastro = validadoresCadastro;
        this.validadoresDelete = validadoresDelete;
    }

    private Cliente buscarCliente(UUID id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoExcpetion("Cliente não foi encontrado!"));
    }

    public InformarClienteDto salvar(CadastrarClienteDto dto) {
        validadoresCadastro.forEach(v -> v.validar(dto));
        Cliente cliente = new Cliente(dto);
        cliente.setStatus(Status.ATIVO);
        clienteRepository.save(cliente);

        return new InformarClienteDto(cliente);
    }

    public void deletar(UUID id){
        validadoresDelete.forEach(v -> v.validar(id));
        Cliente cliente = buscarCliente(id);
        clienteRepository.delete(cliente);
    }

    public Page<InformarClienteDto> buscarClientes(Pageable pageable){
        Page<Cliente> page = clienteRepository.findAll(pageable);

        return page.map(InformarClienteDto::new);
    }

    public InformarClienteDto atualizar(UUID id, AtualizarClienteDto dto) {
        Cliente cliente = buscarCliente(id);
        cliente.atualizar(dto);
        clienteRepository.save(cliente);

        return new InformarClienteDto(cliente);
    }
}
