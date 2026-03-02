package com.devsu.cliente.domain.ports;

import com.devsu.cliente.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Cliente insert(Cliente cliente);

    Cliente update(Cliente cliente);

    void delete(Long id);
}
