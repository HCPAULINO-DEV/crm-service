package com.example.crm_service.entity;

import com.example.crm_service.dto.AtualizarClienteDto;
import com.example.crm_service.dto.CadastrarClienteDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private TipoDocumento tipoDocumento;

    private String documento;

    private String email;

    private String telefone;

    @Embedded
    private Endereco endereco;

    private Status status;

    public Cliente(CadastrarClienteDto dto){
        this.nome = dto.nome();
        this.tipoDocumento = dto.tipoDocumento();
        this.documento = dto.documento();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.endereco = dto.endereco();
    }

    public void atualizar(AtualizarClienteDto dto){
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.tipoDocumento() != null) {
            this.tipoDocumento = dto.tipoDocumento();
        }
        if (dto.documento() != null) {
            this.documento = dto.documento();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
        if (dto.endereco() != null){
            this.endereco = atualizarEndereco(dto.endereco());
        }
        if (dto.status() != null){
            this.status = dto.status();
        }
    }

    private Endereco atualizarEndereco(Endereco dto) {
        return new Endereco(
                dto.cep() != null ? dto.cep() : this.endereco.cep(),
                dto.estado() != null ? dto.estado() : this.endereco.estado(),
                dto.cidade() != null ? dto.cidade() : this.endereco.cidade(),
                dto.bairro() != null ? dto.bairro() : this.endereco.bairro(),
                dto.rua() != null ? dto.rua() : this.endereco.rua(),
                dto.numero() != null ? dto.numero() : this.endereco.numero()
        );
    }

}
