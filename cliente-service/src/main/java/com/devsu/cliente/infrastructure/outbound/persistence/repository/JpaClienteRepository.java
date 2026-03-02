package com.devsu.cliente.infrastructure.outbound.persistence.repository;

import com.devsu.cliente.infrastructure.outbound.persistence.entity.ClienteEntity;
import com.devsu.cliente.infrastructure.outbound.persistence.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {


}
