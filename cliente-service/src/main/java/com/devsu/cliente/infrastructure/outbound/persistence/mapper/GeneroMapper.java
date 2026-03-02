package com.devsu.cliente.infrastructure.outbound.persistence.mapper;

import com.devsu.cliente.domain.model.Genero;
import com.devsu.cliente.infrastructure.outbound.persistence.entity.GeneroEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public GeneroEntity toEntity(Genero genero) {
        if (genero == null) return null;

        return GeneroEntity.builder()
                .id(genero.getId())
                .descripcion(genero.getDescripcion())
                .build();
    }

    public Genero toDomain(GeneroEntity entity) {
        if (entity == null) return null;

        return Genero.builder()
                .id(entity.getId())
                .descripcion(entity.getDescripcion())
                .build();
    }
}