package com.salesianostriana.dam.apirecetas.errors.NotFoundException;

public class IngredienteNotFoundException extends MyEntityNotFoundException{

    public IngredienteNotFoundException(){
        super("Ingrediente no encontrado");
    }
    public IngredienteNotFoundException(String mensaje){
        super(mensaje);
    }
    public IngredienteNotFoundException(Long id){
        super("Ingrediente con id %d no entontrado".formatted(id));
    }
}
