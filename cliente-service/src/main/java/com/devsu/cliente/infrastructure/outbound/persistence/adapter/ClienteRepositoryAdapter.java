package com.devsu.cliente.infrastructure.outbound.persistence.adapter;

import com.devsu.cliente.domain.model.Cliente;
import com.devsu.cliente.domain.ports.ClienteRepository;
import com.devsu.cliente.infrastructure.outbound.persistence.entity.ClienteEntity;
import com.devsu.cliente.infrastructure.outbound.persistence.mapper.ClienteMapper;
import com.devsu.cliente.infrastructure.outbound.persistence.mapper.EstadoMapper;
import com.devsu.cliente.infrastructure.outbound.persistence.mapper.PersonaMapper;
import com.devsu.cliente.infrastructure.outbound.persistence.repository.JpaClienteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final JpaClienteRepository jpaClienteRepository;
    private final ClienteMapper clienteMapper;
    private final PersonaMapper personaMapper;
    private final EstadoMapper estadoMapper;
    public ClienteRepositoryAdapter(JpaClienteRepository jpaClienteRepository,
                                    ClienteMapper clienteMapper,
                                    PersonaMapper personaMapper,
                                    EstadoMapper estadoMapper) {
        this.jpaClienteRepository = jpaClienteRepository;
        this.clienteMapper =clienteMapper;
        this.personaMapper =personaMapper;
        this.estadoMapper =estadoMapper;
    }

    @Override
    public List<Cliente> findAll() {
        return jpaClienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Cliente> findById(Long id) {

        return jpaClienteRepository.findById(id)
                .map(clienteMapper::toDomain);
    }


    @Override
    public Cliente insert(Cliente cliente) {

        return clienteMapper.toDomain(jpaClienteRepository.save(
                clienteMapper.toEntity(cliente)));
    }


    @Override
    public Cliente update(Cliente cliente) {

        ClienteEntity entity = jpaClienteRepository
                .findById(cliente.getId())
                .orElseThrow(() -> new RuntimeException("Cliente not found"));


        entity.setPassword(cliente.getPassword());


        if (cliente.getPersona() != null) {
            entity.setPersona(personaMapper.toEntity(cliente.getPersona()));
        }

        if (cliente.getEstado() != null) {
            entity.setEstado(estadoMapper.toEntity(cliente.getEstado()));
        }

        // 🔹 guardar
        ClienteEntity updated = jpaClienteRepository.save(entity);

        // 🔹 devolver dominio
        return clienteMapper.toDomain(updated);
    }

    @Override
    public void delete(Long id) {

        jpaClienteRepository.deleteById(id);

    }

}
