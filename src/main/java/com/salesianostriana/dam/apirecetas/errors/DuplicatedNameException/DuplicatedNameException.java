package com.salesianostriana.dam.apirecetas.errors.DuplicatedNameException;

public class DuplicatedNameException extends RuntimeException{
    public DuplicatedNameException(){
        super("No se puede repetir el nombre");
    }
    public DuplicatedNameException(String mensaje){
        super(mensaje);
    }
    public DuplicatedNameException(Long id){
        super("No puedes repetir el nombre de %d".formatted(id));
    }
}
