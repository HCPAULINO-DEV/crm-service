package com.example.crm_service.controller;

import com.example.crm_service.dto.CadastrarClienteDto;
import com.example.crm_service.service.ClienteService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

     private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity cadastrar(@Valid @RequestBody CadastrarClienteDto dto, UriComponentsBuilder uriComponentsBuilder){
        var cliente = clienteService.salvar(dto);
        var uri = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(cliente.id()).toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @DeleteMapping
    public ResponseEntity deletar(@PathVariable UUID id){
        clienteService.deletar(id);

        return ResponseEntity.noContent().build();

    }
}
