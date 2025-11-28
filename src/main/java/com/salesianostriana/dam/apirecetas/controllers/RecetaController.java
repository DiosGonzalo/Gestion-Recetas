package com.salesianostriana.dam.apirecetas.controllers;

import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.dto.Recetas.RecetaCmd;
import com.salesianostriana.dam.apirecetas.models.dto.Recetas.RecetaResponse;
import com.salesianostriana.dam.apirecetas.models.dto.ingredientesRecetas.AñadirIngredienteCmd;
import com.salesianostriana.dam.apirecetas.models.dto.ingredientesRecetas.IngredienteInReceta;
import com.salesianostriana.dam.apirecetas.services.RecetaService;
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
@RequestMapping ("/api/v1/recetas")
@Tag(name = "Recetas", description = "Controlar las recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @GetMapping
    @Operation(summary = "Trae todas las recetas")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de recetas obtenida",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RecetaResponse.class)),
                            examples = @ExampleObject(
                                    value = """
                    [
                        {
                            "id": 1,
                            "nombre": "Tarta de manzana",
                            "tiempoPreparacionMin": 60,
                            "dificultad": "MEDIA",
                            "categoriaId": 1,
                            "categoriaNombre": "Postres",
                            "ingredientes": [
                                {"ingredienteId": 2, "nombre": "Huevos", "cantidad": 2, "unidadMedida": "Unidades"}
                            ]
                        },
                        {
                            "id": 2,
                            "nombre": "Sopa de verduras",
                            "tiempoPreparacionMin": 30,
                            "dificultad": "BAJA",
                            "categoriaId": 2,
                            "categoriaNombre": "Platos principales",
                            "ingredientes": []
                        }
                    ]
                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado ninguna receta")
    })
    public List<RecetaResponse> getAll(){
        return recetaService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una receta por el id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Receta encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RecetaResponse.class),
                            examples = @ExampleObject(
                                    value = """
                    {
                        "id": 1,
                        "nombre": "Tarta de manzana",
                        "tiempoPreparacionMin": 60,
                        "dificultad": "MEDIA",
                        "categoriaId": 1,
                        "categoriaNombre": "Postres",
                        "ingredientes": [
                            {"ingredienteId": 2, "nombre": "Huevos", "cantidad": 2, "unidadMedida": "Unidades"},
                            {"ingredienteId": 1, "nombre": "Harina de Trigo", "cantidad": 250, "unidadMedida": "Gramos"}
                        ]
                    }
                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Receta no encontrada (EntidadNoEncontradaException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            )
    })
    public RecetaResponse recetaPorId(@PathVariable Long id){
        return RecetaResponse.of(recetaService.getById(id));
    }


    @PutMapping("/crear")
    @Operation(summary = "Crea una receta")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RecetaCmd.class),
                    examples = @ExampleObject(
                            value = """
                                {
                                    "nombre": "Ensalada César",
                                    "tiempoPreparacionMin": 15,
                                    "dificultad": "BAJA",
                                    "categoriaId": 3
                                }
                                """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Receta creada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RecetaResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                            "id": 3,
                                            "nombre": "Ensalada César",
                                            "tiempoPreparacionMin": 15,
                                            "dificultad": "BAJA",
                                            "categoriaId": 3,
                                            "categoriaNombre": "Entrantes",
                                            "ingredientes": []
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409", description = "Nombre ya existente (NombreDuplicadoException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400", description = "Tiempo de preparación inválido (TiempoInvalidoException) o datos malformados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            )
    })
    public Receta crear(@RequestBody RecetaCmd cmd){
        return recetaService.create(cmd);
    }


    @PostMapping("/edit/{id}")
    @Operation(summary = "Edita una receta")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RecetaCmd.class),
                    examples = @ExampleObject(
                            value = """
                                {
                                    "nombre": "Tarta de Manzana (Versión Rápida)",
                                    "tiempoPreparacionMin": 45,
                                    "dificultad": "BAJA",
                                    "categoriaId": 1
                                }
                                """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Receta editada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RecetaResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                            "id": 1,
                                            "nombre": "Tarta de Manzana (Versión Rápida)",
                                            "tiempoPreparacionMin": 45,
                                            "dificultad": "BAJA",
                                            "categoriaId": 1,
                                            "categoriaNombre": "Postres",
                                            "ingredientes": []
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Receta no encontrada (EntidadNoEncontradaException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409", description = "Nombre ya existente (NombreDuplicadoException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400", description = "Tiempo de preparación inválido (TiempoInvalidoException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            )
    })
    public Receta edit(@PathVariable Long id, @RequestBody RecetaCmd cmd){
        return recetaService.edit(cmd,id);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "borra una receta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Receta eliminada con éxito (Sin contenido)"
            ),
            @ApiResponse(
                    responseCode = "404", description = "Receta no encontrada (EntidadNoEncontradaException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Void> delete(@PathVariable Long id){
        recetaService.borrar(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{recetaId}/ingredientes")
    @Operation(summary = "Obtiene una receta con la lista detallada de sus ingredientes")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Receta con ingredientes encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = IngredienteInReceta.class),
                            examples = @ExampleObject(
                                    value = """
                    {
                        "id": 1,
                        "nombre": "Tarta de manzana",
                        "tiempoPreparacionMin": 60,
                        "dificultad": "MEDIA",
                        "categoriaId": 1,
                        "categoriaNombre": "Postres",
                        "ingredientes": [
                            {"ingredienteId": 2, "nombre": "Huevos", "cantidad": 2, "unidadMedida": "Unidades"},
                            {"ingredienteId": 1, "nombre": "Harina de Trigo", "cantidad": 250, "unidadMedida": "Gramos"}
                        ]
                    }
                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Receta no encontrada (EntidadNoEncontradaException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            )
    })
    public IngredienteInReceta recetaConIngredientes(@PathVariable Long id){
        return recetaService.recetaConIngredientes(id);
    }

    @PostMapping("/{recetaId}")
    @Operation(summary = "Añade un ingrediente a una receta específica")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AñadirIngredienteCmd.class),
                    examples = @ExampleObject(
                            value = """
                                {
                                    "ingredienteId": 5,
                                    "cantidad": 100,
                                    "unidadMedida": "Mililitros"
                                }
                                """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Ingrediente añadido con éxito a la receta",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = IngredienteInReceta.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                            "id": 1,
                                            "nombre": "Tarta de manzana",
                                            "tiempoPreparacionMin": 60,
                                            "dificultad": "MEDIA",
                                            "categoriaId": 1,
                                            "categoriaNombre": "Postres",
                                            "ingredientes": [
                                                {"ingredienteId": 2, "nombre": "Huevos", "cantidad": 2, "unidadMedida": "Unidades"},
                                                {"ingredienteId": 5, "nombre": "Mantequilla", "cantidad": 100, "unidadMedida": "Gramos"}
                                            ]
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Receta o Ingrediente no encontrado (EntidadNoEncontradaException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409", description = "Ingrediente ya presente en la receta (IngredienteYaAnadidoException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<IngredienteInReceta> addIngrediente(
            @PathVariable Long id,
            @RequestBody AñadirIngredienteCmd cmd) {

        Receta receta = recetaService.addIngredienteReceta(id, cmd);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IngredienteInReceta.of(receta));
    }


}