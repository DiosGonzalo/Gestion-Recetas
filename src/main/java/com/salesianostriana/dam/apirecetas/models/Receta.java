package com.salesianostriana.dam.apirecetas.models;

import com.salesianostriana.dam.apirecetas.models.enums.Dificultad;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Receta {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer tiempoPreparacionMin;
    @Enumerated
    private Dificultad dificultad;

    @OneToMany(mappedBy = "receta", fetch = FetchType.EAGER)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Receta_Ingrediente> ingredientes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
