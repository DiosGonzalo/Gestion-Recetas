package com.salesianostriana.dam.apirecetas.models.dto;

import com.salesianostriana.dam.apirecetas.models.Categoria;
import com.salesianostriana.dam.apirecetas.models.Receta;

public record CrearCategoriaCmd (

        String nombre,
        String descripcion

) {
    public Categoria toEntity(){
        return Categoria.builder()
                .nombre(this.nombre())
                .descripcion(this.descripcion())
                .build();
    }



}
