package com.salesianostriana.dam.apirecetas.errors.NotFoundException;

public class RecetaNotFoundException extends MyEntityNotFoundException{
    public RecetaNotFoundException(){
        super("Receta no encontrada");
    }
    public RecetaNotFoundException(String mensaje){
        super(mensaje);
    }
    public RecetaNotFoundException(Long id){
        super("La receta con id %d no ha sido encontrada".formatted(id));
    }
}
