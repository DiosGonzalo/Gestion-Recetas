package com.salesianostriana.dam.apirecetas.controllers;


import com.salesianostriana.dam.apirecetas.Repository.IngredienteRespository;
import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import com.salesianostriana.dam.apirecetas.models.dto.IngredienteCmd;
import com.salesianostriana.dam.apirecetas.models.dto.IngredienteResponse;
import com.salesianostriana.dam.apirecetas.services.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Ingredientes", description = "Se gestionan todos los componentes")
@RequestMapping ("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @GetMapping
    @Operation(summary = "Obtiene todos los ingredientes")
    public List<IngredienteResponse> getAll(){
        return ingredienteService.getAll()
                .stream()
                .map(ingrediente -> IngredienteResponse.of(ingrediente))
                .toList();
    }

    @PutMapping ("/crear")
    @Operation(summary = "Crea un ingrediente")
    public ResponseEntity<IngredienteResponse> create( @RequestBody IngredienteCmd cmd){
        Ingrediente nuevo = cmd.toEntity();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IngredienteResponse.of(nuevo));
    }





}
