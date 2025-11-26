package com.salesianostriana.dam.apirecetas.models.dto;

import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.Receta_Ingrediente;

import java.util.List;

public record IngredienteResponse (
        Long id,
        String nombre,
        List<Receta_Ingrediente> recetas
){
    public static IngredienteResponse of(Ingrediente ingrediente){
        return new IngredienteResponse(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getRecetas()
        );
    }
}
