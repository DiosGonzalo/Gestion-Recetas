package com.salesianostriana.dam.apirecetas.controllers;


import com.salesianostriana.dam.apirecetas.models.Categoria;
import com.salesianostriana.dam.apirecetas.models.dto.categorias.CategoriaResponse;
import com.salesianostriana.dam.apirecetas.models.dto.categorias.CrearCategoriaCmd;
import com.salesianostriana.dam.apirecetas.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag( name = "Categorias", description = " Operaciones con las categorias")
@RequestMapping("/api/v1/categoria")
@RestController
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "trae todas las categorias")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de categorías obtenida",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoriaResponse.class)),
                            examples = @ExampleObject(
                                    value = """
                    [
                        {
                            "id": 1,
                            "nombre": "Postres",
                            "descripcion": "Recetas dulces y postres caseros"
                        },
                        {
                            "id": 2,
                            "nombre": "Platos principales",
                            "descripcion": "Recetas para comidas y cenas"
                        },
                        {
                            "id": 3,
                            "nombre": "Entrantes",
                            "descripcion": "Aperitivos y primeros platos"
                        }
                    ]
                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado ninguna categoria")
    })

    public List<CategoriaResponse> getAll(){
        return categoriaService.getAllCategoria()
                .stream()
                .map(categoria -> CategoriaResponse.of(categoria))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae una sola categoría")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Categoria encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategoriaResponse.class),
                            examples = @ExampleObject(
                                    value= """
                                        {
                                            "id": 1,
                                            "nombre": "Postres",
                                            "descripcion": "Recetas dulces y postres caseros"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Categoría no encontrada (EntidadNoEncontradaException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            )
    })

    public ResponseEntity<CategoriaResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                CategoriaResponse.of(categoriaService.getCategoriaById(id)));

    }

    @PostMapping("/create")
    @Operation(summary = "Crea una categoría")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CrearCategoriaCmd.class),
                    examples = @ExampleObject(
                            value = """
                                {
                                    "nombre": "Veganas",
                                    "descripcion": "Recetas sin productos de origen animal"
                                }
                                """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Categoria creada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategoriaResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                            "id": 4,
                                            "nombre": "Veganas",
                                            "descripcion": "Recetas sin productos de origen animal"
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
            )
    })
    public ResponseEntity<CategoriaResponse> create(
            @RequestBody CrearCategoriaCmd cmd
    ){
        Categoria nueva = categoriaService.saveCategoria(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaResponse.of(nueva));
    }


    @PutMapping("/edit/{id}")
    @Operation(summary = "Edita una categoría por id")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CrearCategoriaCmd.class),
                    examples = @ExampleObject(
                            value = """
                                {
                                    "nombre": "Postres",
                                    "descripcion": "Recetas dulces, tartas y postres caseros"
                                }
                                """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoría editada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategoriaResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                            "id": 1,
                                            "nombre": "Postres",
                                            "descripcion": "Recetas dulces, tartas y postres caseros"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Categoría no encontrada (EntidadNoEncontradaException)",
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
            )
    })
    public CategoriaResponse edit(@PathVariable Long id, @RequestBody CrearCategoriaCmd cmd){
        return CategoriaResponse.of(categoriaService.edit(cmd,id));
    }


    @DeleteMapping ("/delete/{id}")
    @Operation(summary = "Borra una categoría por id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Categoría eliminada con éxito "
            ),
            @ApiResponse(
                    responseCode = "404", description = "Categoría no encontrada (EntidadNoEncontradaException)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.web.ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}