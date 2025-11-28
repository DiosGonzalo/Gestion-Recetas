package com.salesianostriana.dam.apirecetas.models.dto.Recetas;

import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.enums.Dificultad;

public record RecetaCmd (

        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad
){
    public Receta toEntity(RecetaCmd cmd){
        return Receta.builder()
                .nombre(this.nombre)
                .tiempoPreparacionMin(this.tiempoPreparacionMin)
                .dificultad(this.dificultad)
                .build();
    }
}
