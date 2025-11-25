package com.salesianostriana.dam.apirecetas.errors.NotFoundException;

public class MyEntityNotFoundException extends RuntimeException
{
    public MyEntityNotFoundException(){
        super ("Entity not found");
    }

    public MyEntityNotFoundException(String mensaje){
        super(mensaje);
    }

    public MyEntityNotFoundException(Long id){
        super("No se ha encontrado la entidad con el id: %d".formatted(id));
    }

}
