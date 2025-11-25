package com.salesianostriana.dam.apirecetas.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String nombre;
    @OneToMany(mappedBy = "ingrediente", fetch = FetchType.EAGER)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Receta_Ingrediente> recetas = new ArrayList<>();


    /***
     * para la asociacion, poruqe la tabla de en medio tien que tener mas columnas
     * Solucion crear una tercera entidad ingredientes-recetas y cambiar a many to one de recetas e ingredientes
     *
     */
}
