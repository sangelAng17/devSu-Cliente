package com.devsu.cliente.application;


import com.devsu.cliente.domain.model.Persona;
import com.devsu.cliente.domain.ports.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;

    @Test
    void shouldReturnAllPersonas() {
        Persona persona = new Persona();
        when(personaRepository.findAll()).thenReturn(List.of(persona));

        List<Persona> result = personaService.findAll();

        assertEquals(1, result.size());
        verify(personaRepository).findAll();
    }
}
