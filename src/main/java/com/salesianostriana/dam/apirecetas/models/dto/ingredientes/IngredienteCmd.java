package com.salesianostriana.dam.apirecetas.models.dto.ingredientes;

import com.salesianostriana.dam.apirecetas.models.Ingrediente;

public record IngredienteCmd(
        String nombre
) {

    public Ingrediente toEntity(){
        return Ingrediente.builder()
                .nombre(this.nombre)
                .build();
    }
}
