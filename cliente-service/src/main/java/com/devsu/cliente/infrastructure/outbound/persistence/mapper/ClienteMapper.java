package com.devsu.cliente.infrastructure.outbound.persistence.mapper;

import com.devsu.cliente.domain.model.Cliente;
import com.devsu.cliente.infrastructure.outbound.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final PersonaMapper personaMapper;
    private final EstadoMapper estadoMapper;

    public ClienteMapper(PersonaMapper personaMapper,
                         EstadoMapper estadoMapper) {
        this.personaMapper = personaMapper;
        this.estadoMapper = estadoMapper;
    }

    // 🔹 Domain → Entity
    public ClienteEntity toEntity(Cliente cliente) {
        if (cliente == null) return null;

        return ClienteEntity.builder()
                .id(cliente.getId())
                .password(cliente.getPassword())
                .persona(personaMapper.toEntity(cliente.getPersona()))
                .estado(estadoMapper.toEntity(cliente.getEstado()))
                .build();
    }

    // 🔹 Entity → Domain
    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) return null;

        return Cliente.builder()
                .id(entity.getId())
                .password(entity.getPassword())
                .persona(personaMapper.toDomain(entity.getPersona()))
                .estado(estadoMapper.toDomain(entity.getEstado()))
                .build();
    }


}
