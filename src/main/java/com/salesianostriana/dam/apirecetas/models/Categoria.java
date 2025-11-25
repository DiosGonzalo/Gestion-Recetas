package com.salesianostriana.dam.apirecetas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String descripcion;

}
