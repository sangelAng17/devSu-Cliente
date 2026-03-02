package com.devsu.cliente.application;


import com.devsu.cliente.domain.model.Persona;
import com.devsu.cliente.domain.ports.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Optional<Persona> findUser(Long id) {
        return personaRepository.findById(id);
    }

    public Persona insert(Persona persona){return personaRepository.insert(persona);}

    public Persona update(Persona persona){return personaRepository.update(persona);}

    public void delete(Long id){personaRepository.delete(id);}
}
