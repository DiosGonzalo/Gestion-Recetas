package com.salesianostriana.dam.apirecetas.models.dto;

import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.enums.Dificultad;

import java.util.List;

public record IngredienteInReceta (
 Long id,
 String nombre,
 Integer tiempoPreparacionMin,
 Dificultad dificultad,
 String categoriaNombre,
 List<IngredienteCantCmd> ingredientes
){
    public static IngredienteInReceta of (Receta receta){
        return new RecetaResponse(
                receta.getId(),
                receta.getNombre(),
                receta.getTiempoPreparacionMin(),
                receta.getDificultad(),
                receta.getCategoria().getNombre(),
                receta.getIngredientes().stream()
                        .map(IngredienteCantCmd::of)
                        .toList()
        );
    }
}
