package com.salesianostriana.dam.apirecetas.models.dto.categorias;

import com.salesianostriana.dam.apirecetas.models.Categoria;

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
