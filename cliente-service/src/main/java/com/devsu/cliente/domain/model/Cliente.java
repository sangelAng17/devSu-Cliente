package com.devsu.cliente.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Long id;
    private String password;

    private Persona persona;

    private Estado estado;
}
