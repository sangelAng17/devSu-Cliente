package com.devsu.cliente.application;


import com.devsu.cliente.domain.model.Cliente;
import com.devsu.cliente.domain.ports.ClienteRepository;
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
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void shouldReturnAllClientes() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> result = clienteService.findAll();

        assertEquals(1, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(clienteRepository).findById(1L);
    }

    @Test
    void shouldInsertCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.insert(cliente)).thenReturn(cliente);

        Cliente result = clienteService.insert(cliente);

        assertNotNull(result);
        verify(clienteRepository).insert(cliente);
    }

    @Test
    void shouldUpdateCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.update(cliente)).thenReturn(cliente);

        Cliente result = clienteService.update(cliente);

        assertNotNull(result);
        verify(clienteRepository).update(cliente);
    }

    @Test
    void shouldDeleteCliente() {
        Long id = 1L;

        clienteService.delete(id);

        verify(clienteRepository).delete(id);
    }

}