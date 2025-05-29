package com.example.crm_service.service;

import com.example.crm_service.dto.CadastrarClienteDto;
import com.example.crm_service.dto.InformarClienteDto;
import com.example.crm_service.entity.Cliente;
import com.example.crm_service.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public InformarClienteDto salvar(CadastrarClienteDto dto) {
        if (clienteRepository.existsByDocumento(dto.documento())){
            throw new RuntimeException("ERRO: Documento já existente");
        }
        if (clienteRepository.existsByEmail(dto.email())){
            throw new RuntimeException("ERRO: Email já existente");
        }
        if (clienteRepository.existsByNome(dto.nome())){
            throw new RuntimeException("ERRO: Nome ou Razão Social já existente");
        }
        Cliente cliente = new Cliente(dto);
        clienteRepository.save(cliente);

        return new InformarClienteDto(cliente);
    }

    public void deletar(UUID id){
        Cliente cliente = buscarCliente(id);
        clienteRepository.delete(cliente);
    }

    private Cliente buscarCliente(UUID id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não foi encontrado!"))
    }
}
