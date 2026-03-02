package com.devsu.cliente.domain.ports;

import com.devsu.cliente.domain.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {

    List<Persona> findAll();

    Optional<Persona> findById(Long id);

    Persona insert(Persona user);

    Persona update(Persona persona);

    void delete(Long id);
}
