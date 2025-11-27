package com.salesianostriana.dam.apirecetas.models.dto;

import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.Receta_Ingrediente;

public record AñadirIngredienteCmd (
        Long ingredienteId,
        double cantidad,
        String unidad
){
    public Receta_Ingrediente toEntity(Ingrediente ingrediente, Receta receta){
        return Receta_Ingrediente.builder()
                .ingrediente(ingrediente)
                .receta(receta)
                .cantidad(this.cantidad)
                .unidad(this.unidad)
                .build();
    }
}
