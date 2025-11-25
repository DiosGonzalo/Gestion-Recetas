package com.salesianostriana.dam.apirecetas.errors;

import com.salesianostriana.dam.apirecetas.errors.NotFoundException.IngredienteNotFoundException;

public class IngredienteAlredyAddedException extends RuntimeException{
    public IngredienteAlredyAddedException(){
        super("Ingrediente ya creado");
    }
    public IngredienteAlredyAddedException(String mensaje){
        super(mensaje);
    }
    public IngredienteAlredyAddedException(Long id){
        super("No puedes crear el ingrediente con id %d porque ya esta creado");
    }
}
