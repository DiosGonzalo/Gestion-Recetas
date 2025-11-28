package com.salesianostriana.dam.apirecetas.models.dto.categorias;

import com.salesianostriana.dam.apirecetas.models.Categoria;

public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion
) {
    public static CategoriaResponse of(Categoria categoria){
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}
