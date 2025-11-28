package com.salesianostriana.dam.apirecetas.controllers;


import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import com.salesianostriana.dam.apirecetas.models.dto.ingredientes.IngredienteCmd;
import com.salesianostriana.dam.apirecetas.models.dto.ingredientes.IngredienteResponse;
import com.salesianostriana.dam.apirecetas.services.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Ingredientes", description = "Se gestionan todos los componentes")
@RequestMapping ("/api/v1/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @GetMapping
    @Operation(summary = "Obtiene todos los ingredientes")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de ingredientes obtenida",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = IngredienteResponse.class)),
                            examples = @ExampleObject(
                                    value = """
                    [
                        {
                            "id": 1,
                            "nombre": "Harina de Trigo",
                        },
                        {
                            "id": 2,
                            "nombre": "Huevos",
                        },
                        {
                            "id": 3,
                            "nombre": "Leche",
                        }
                    ]
                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado ningún ingrediente")
    })
    public List<IngredienteResponse> getAll(){
        return ingredienteService.getAll()
                .stream()
                .map(ingrediente -> IngredienteResponse.of(ingrediente))
                .toList();
    }

    @PostMapping ("/crear")
    @Operation(summary = "Crea un ingrediente")
    @RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = IngredienteCmd.class),
                    examples = @ExampleObject(
                            value = """
                                {
                                    "nombre": "Sal",
                                }
                                """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Ingrediente creado con éxito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = IngredienteResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                            "id": 4,
                                            "nombre": "Sal",
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400", description = "Datos de entrada inválidos"
            )
    })
    public ResponseEntity<IngredienteResponse> create( @RequestBody IngredienteCmd cmd){
        Ingrediente nuevo = cmd.toEntity();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IngredienteResponse.of(nuevo));
    }

}