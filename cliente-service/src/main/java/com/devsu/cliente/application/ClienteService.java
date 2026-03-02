package com.devsu.cliente.application;


import com.devsu.cliente.domain.model.Cliente;
import com.devsu.cliente.domain.ports.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente insert(Cliente persona){return clienteRepository.insert(persona);}

    public Cliente update(Cliente persona){return clienteRepository.update(persona);}

    public void delete(Long id){clienteRepository.delete(id);}
}
