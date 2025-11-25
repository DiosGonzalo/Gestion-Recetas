package com.salesianostriana.dam.apirecetas.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ingrediente {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String nombre;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "ingrediente_receta",
            joinColumns = @JoinColumn(name = "ingrediente_id"),
            inverseJoinColumns = @JoinColumn(name = "receta_id")
    )
    private List<Receta> recetas = new ArrayList<>();

    /***
     * para la asociacion, poruqe la tabla de en medio tien que tener mas columnas
     * Solucion crear una tercera entidad ingredientes-recetas y cambiar a many to one de recetas e ingredientes
     *
     */
}
