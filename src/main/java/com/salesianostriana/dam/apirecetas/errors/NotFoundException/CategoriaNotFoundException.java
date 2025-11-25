package com.salesianostriana.dam.apirecetas.errors.NotFoundException;

public class CategoriaNotFoundException extends MyEntityNotFoundException{
    public CategoriaNotFoundException(){
        super("Categoria no encontrada");
    }
    public CategoriaNotFoundException(String mensaje){
        super(mensaje);
    }
    public CategoriaNotFoundException (Long id){
        super("La categoria con el id %d no ha sido encontrada".formatted(id));
    }
}
