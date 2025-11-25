package com.salesianostriana.dam.apirecetas.models;

import com.salesianostriana.dam.apirecetas.models.enums.Dificultad;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Receta {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private Integer tiempoPreparacionMin;
    @Enumerated
    private Dificultad dificultad;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToMany(mappedBy = "recetas")
    private List<Ingrediente> ingredientes = new ArrayList<>();
}
