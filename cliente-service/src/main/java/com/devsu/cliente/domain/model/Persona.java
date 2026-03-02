package com.devsu.cliente.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    private Long id;
    private String nombre;

    private Genero genero;
    private String edad;
    private Long identificacion;
    private String direccion;
    private Long telefono;

}
