package com.devsu.cliente.infrastructure.outbound.persistence.entity;

import com.devsu.cliente.domain.model.Genero;
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
@Table(name = "persona",schema = "public")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_genero_pk")
    private GeneroEntity genero;
    private String edad;
    private Long identificacion;
    private String direccion;
    private Long telefono;


}
