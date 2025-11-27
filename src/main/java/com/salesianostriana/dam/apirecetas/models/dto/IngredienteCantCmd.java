package com.salesianostriana.dam.apirecetas.models.dto;

import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import com.salesianostriana.dam.apirecetas.models.Receta_Ingrediente;

public record IngredienteCantCmd(
        Long id,
        String nombre,
        double cantidad,
        String unidad
) {

    public static IngredienteCantCmd of (Receta_Ingrediente ingrediente){
        return new IngredienteCantCmd(
                ingrediente.getIngrediente().getId(),
                ingrediente.getIngrediente().getNombre(),
                ingrediente.getCantidad(),
                ingrediente.getUnidad()
        );
    }
}
