package com.devsu.cliente.infrastructure.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "cliente",schema = "public")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_persona_pk")
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "id_estado_pk")
    private EstadoEntity  estado;
}
