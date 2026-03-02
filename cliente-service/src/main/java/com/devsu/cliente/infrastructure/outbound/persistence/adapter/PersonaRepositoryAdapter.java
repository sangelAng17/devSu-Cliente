package com.devsu.cliente.infrastructure.outbound.persistence.adapter;

import com.devsu.cliente.domain.model.Persona;
import com.devsu.cliente.domain.ports.PersonaRepository;
import com.devsu.cliente.infrastructure.outbound.persistence.entity.PersonaEntity;
import com.devsu.cliente.infrastructure.outbound.persistence.mapper.GeneroMapper;
import com.devsu.cliente.infrastructure.outbound.persistence.mapper.PersonaMapper;
import com.devsu.cliente.infrastructure.outbound.persistence.repository.JpaPersonaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonaRepositoryAdapter implements PersonaRepository {


    private final JpaPersonaRepository jpaPersonaRepository;
    private final PersonaMapper personaMapper;
    private final GeneroMapper generoMapper;

    public PersonaRepositoryAdapter(JpaPersonaRepository jpaPersonaRepository,
                                    PersonaMapper personaMapper,
                                    GeneroMapper generoMapper) {
        this.jpaPersonaRepository = jpaPersonaRepository;
        this.personaMapper = personaMapper;
        this.generoMapper =generoMapper;
    }

    @Override
    public List<Persona> findAll() {
        return jpaPersonaRepository.findAll()
                .stream()
                .map(personaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Persona> findById(Long id) {

        return jpaPersonaRepository.findById(id)
                .map(personaMapper::toDomain);
    }

    @Override
    public Persona insert(Persona persona) {

        return personaMapper.toDomain(jpaPersonaRepository.save(
                personaMapper.toEntity(persona)));
    }


    @Override
    public Persona update(Persona persona) {

        PersonaEntity entity = jpaPersonaRepository
                .findByIdentificacion(persona.getIdentificacion())
                .orElseThrow(() -> new RuntimeException("Persona not found"));

        entity.setNombre(persona.getNombre());
        entity.setEdad(persona.getEdad());
        entity.setIdentificacion(persona.getIdentificacion());
        entity.setDireccion(persona.getDireccion());
        entity.setTelefono(persona.getTelefono());

        entity.setGenero(generoMapper.toEntity(persona.getGenero()));

        PersonaEntity updated = jpaPersonaRepository.save(entity);

        return personaMapper.toDomain(updated);
    }

    @Override
    public void delete(Long id) {

        jpaPersonaRepository.deleteById(id);

    }

}
