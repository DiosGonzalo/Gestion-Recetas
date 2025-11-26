package com.salesianostriana.dam.apirecetas.models.dto;

import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.Receta_Ingrediente;

import java.util.List;

public record IngredienteCmd(
        String nombre
) {

    public Ingrediente toEntity(){
        return Ingrediente.builder()
                .nombre(this.nombre)
                .build();
    }
}
