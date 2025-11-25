package com.salesianostriana.dam.apirecetas.errors;

public class TiempoInvalidoException extends RuntimeException{
    public TiempoInvalidoException(){
        super("Tiempo invalido");
    }
    public TiempoInvalidoException(String mensaje){
        super(mensaje);
    }
    public TiempoInvalidoException(Long id){
        super("%d".formatted(id));
    }
}
