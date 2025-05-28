package com.example.crm_service.repository;

import com.example.crm_service.entity.Cliente;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    boolean existsByDocumento(String documento);

    boolean existsByEmail(String documento);

    boolean existsByNome(String documento);
}
