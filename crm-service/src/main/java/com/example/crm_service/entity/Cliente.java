package com.example.crm_service.entity;

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

    public Cliente(CadastrarClienteDto dto){
        this.nome = dto.nome();
        this.tipoDocumento = dto.tipoDocumento();
        this.documento = dto.documento();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.endereco = dto.endereco();
    }

}
