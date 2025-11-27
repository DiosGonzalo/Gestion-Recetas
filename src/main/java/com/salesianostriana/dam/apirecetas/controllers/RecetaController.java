package com.salesianostriana.dam.apirecetas.controllers;

import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.dto.*;
import com.salesianostriana.dam.apirecetas.services.RecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/recetas")
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
                    
                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado ninguna categoria")
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
                        "ingredientes": []
                    }
                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    public RecetaResponse recetaPorId(@PathVariable Long id){  // ← @PathVariable, no @RequestParam
        return RecetaResponse.of(recetaService.getById(id));  // ← SIN "new"
    }


    @PutMapping("/crear")
    @Operation(summary = "Crea una receta")

    public Receta crear(RecetaCmd cmd){
        return recetaService.create(cmd);
    }


    @PostMapping("/edit/{id}")
    @Operation(summary = "Edita una receta")
    public Receta edit(@PathVariable Long id, RecetaCmd cmd){
        return recetaService.edit(cmd,id);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "borra una receta")
    public void delete(@PathVariable Long id){
        recetaService.borrar(id);
    }


    @GetMapping("/{recetaId}")
    @Operation(summary = "da una receta con la lista de ingredientes")
    public IngredienteInReceta recetaConIngredientes(@PathVariable Long id){
        return recetaService.recetaConIngredientes(id);
    }

    @PostMapping("/{id}/ingredientes")
    public ResponseEntity<IngredienteInReceta> addIngrediente(
            @PathVariable Long id,
            @RequestBody AñadirIngredienteCmd cmd) {

        Receta receta = recetaService.addIngredienteReceta(id, cmd);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IngredienteInReceta.of(receta));
    }


}
