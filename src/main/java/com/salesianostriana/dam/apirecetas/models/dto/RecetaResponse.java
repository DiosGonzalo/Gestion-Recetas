package com.salesianostriana.dam.apirecetas.models.dto;

import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.enums.Dificultad;

public record RecetaResponse (
        Long id,
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad

) {
    public static RecetaResponse of(Receta receta){
        return new RecetaResponse(
                receta.getId(),
                receta.getNombre(),
                receta.getTiempoPreparacionMin(),
                receta.getDificultad()
        );
    }
}
