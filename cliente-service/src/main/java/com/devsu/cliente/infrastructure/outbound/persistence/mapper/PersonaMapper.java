package com.devsu.cliente.infrastructure.outbound.persistence.mapper;

import com.devsu.cliente.domain.model.Persona;
import com.devsu.cliente.infrastructure.outbound.persistence.entity.PersonaEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    private final GeneroMapper generoMapper;

    public PersonaMapper(GeneroMapper generoMapper) {
        this.generoMapper = generoMapper;
    }

    // 🔹 Domain → Entity
    public PersonaEntity toEntity(Persona persona) {
        if (persona == null) return null;

        return PersonaEntity.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .genero(generoMapper.toEntity(persona.getGenero()))
                .edad(persona.getEdad())
                .identificacion(persona.getIdentificacion())
                .direccion(persona.getDireccion())
                .telefono(persona.getTelefono())
                .build();
    }

    // 🔹 Entity → Domain
    public Persona toDomain(PersonaEntity entity) {
        if (entity == null) return null;

        return Persona.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .genero(generoMapper.toDomain(entity.getGenero()))
                .edad(entity.getEdad())
                .identificacion(entity.getIdentificacion())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .build();
    }
}