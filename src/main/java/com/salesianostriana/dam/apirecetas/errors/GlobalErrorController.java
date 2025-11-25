package com.salesianostriana.dam.apirecetas.errors;

import com.salesianostriana.dam.apirecetas.errors.DuplicatedNameException.DuplicatedNameException;
import com.salesianostriana.dam.apirecetas.errors.NotFoundException.MyEntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyEntityNotFoundException.class)
    public ProblemDetail myEntityNotFound(
            MyEntityNotFoundException ex
    ){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problemDetail.setDetail("No encontrado");
        problemDetail.setType(
                URI.create("http://dam.salesianos-triana.com/not-found")
        );
        return problemDetail;
    }

    @ExceptionHandler(DuplicatedNameException.class)
    public ProblemDetail duplicatedName(
            DuplicatedNameException ex
    ){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problemDetail.setTitle("Nombre repetido");
        problemDetail.setType(
                URI.create("http://dam.salesianos-triana.com/Nombre-repetido"));
        return problemDetail;

    }

    @ExceptionHandler(IngredienteAlredyAddedException.class)
    public ProblemDetail ingredienteAlredyAdded(
            IngredienteAlredyAddedException ex
    ){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,ex.getMessage()
        );
        problemDetail.setTitle("Ingrediente alredy in");
        problemDetail.setType(
                URI.create("http://dam.salesianos-triana.com/ingredienteAlredyIn")
        );
        return problemDetail;
    }

    @ExceptionHandler(TiempoInvalidoException.class)
    public ProblemDetail invalidTime(
            TiempoInvalidoException ex
    ){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problemDetail.setTitle("Invalid run time");
        problemDetail.setType(
                URI.create("http://dam.salesianos-triana.com/invalidRunTime")
        );
        return problemDetail;
    }


}
