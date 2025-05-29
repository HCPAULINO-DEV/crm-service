package com.example.crm_service.service;

import com.example.crm_service.dto.CadastrarClienteDto;
import com.example.crm_service.dto.InformarClienteDto;
import com.example.crm_service.entity.Cliente;
import com.example.crm_service.entity.Status;
import com.example.crm_service.infra.exception.ClienteNaoEncontradoExcpetion;
import com.example.crm_service.infra.exception.DeletarClienteAtivoException;
import com.example.crm_service.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        cliente.setStatus(Status.ATIVO);
        clienteRepository.save(cliente);

        return new InformarClienteDto(cliente);
    }

    public void deletar(UUID id){
        Cliente cliente = buscarCliente(id);
        if (cliente.getStatus().equals(Status.ATIVO)){
            throw new DeletarClienteAtivoException("Para deletar um cliente ele deve conter o status de INATIVO!");
        }
        clienteRepository.delete(cliente);
    }

    public Page<InformarClienteDto> buscarClientes(Pageable pageable){
        Page<Cliente> page = clienteRepository.findAll(pageable);

        return page.map(InformarClienteDto::new);
    }

    private Cliente buscarCliente(UUID id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoExcpetion("Cliente não foi encontrado!"));
    }
}
