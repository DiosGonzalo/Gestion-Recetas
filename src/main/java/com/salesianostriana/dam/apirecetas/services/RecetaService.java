package com.salesianostriana.dam.apirecetas.services;

import com.salesianostriana.dam.apirecetas.errors.DuplicatedNameException.DuplicatedNameException;
import com.salesianostriana.dam.apirecetas.errors.NotFoundException.MyEntityNotFoundException;
import com.salesianostriana.dam.apirecetas.errors.TiempoInvalidoException;
import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.Receta_Ingrediente;
import com.salesianostriana.dam.apirecetas.models.dto.ingredientesRecetas.AñadirIngredienteCmd;
import com.salesianostriana.dam.apirecetas.models.dto.ingredientesRecetas.IngredienteInReceta;
import com.salesianostriana.dam.apirecetas.models.dto.Recetas.RecetaCmd;
import com.salesianostriana.dam.apirecetas.models.dto.Recetas.RecetaResponse;
import com.salesianostriana.dam.apirecetas.Repository.RecetaRepository;
import com.salesianostriana.dam.apirecetas.Repository.IngredienteRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final IngredienteRespository ingredienteRespository;

    public List<RecetaResponse> getAll(){
        return recetaRepository.findAll()
                .stream()
                .map(RecetaResponse::of)
                .toList();
    }

    public Receta getById(Long id){
        return recetaRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    public Receta create(RecetaCmd cmd){
        if(StringUtils.hasText(cmd.nombre())){
            throw  new TiempoInvalidoException("Nombre no puede estar vacio");
        }

        if(recetaRepository.existsByNombre(cmd.nombre())){
            throw new DuplicatedNameException();
        }
        Receta nueva = cmd.toEntity(cmd);

        return recetaRepository.save(nueva);
    }

    public Receta edit(RecetaCmd cmd, Long id){
        return recetaRepository.findById(id)
                .map(receta -> {
                    receta.setNombre(cmd.nombre());
                    receta.setDificultad(cmd.dificultad());
                    receta.setTiempoPreparacionMin(cmd.tiempoPreparacionMin());
                    return recetaRepository.save(receta);
                })
                .orElseThrow(TiempoInvalidoException::new);
    }

    public void borrar(Long id){
        recetaRepository.delete(recetaRepository.findById(id)
                .orElseThrow(TiempoInvalidoException::new));
    }

    public IngredienteInReceta recetaConIngredientes(Long id){
        Receta receta = recetaRepository.findById(id).orElseThrow(TiempoInvalidoException::new);
        return IngredienteInReceta.of(receta);
    }

    public Receta addIngredienteReceta(Long idReceta, AñadirIngredienteCmd cmd) {

        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la receta con ID: " + idReceta));

        Ingrediente ingrediente = ingredienteRespository.findById(cmd.ingredienteId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el ingrediente con ID: " + cmd.ingredienteId()));


        Receta_Ingrediente relacion = cmd.toEntity(ingrediente, receta);

        if(relacion.getCantidad()<= 0){
            throw new TiempoInvalidoException("No puede tener una cantidad igual a 0 o negativa");
        }

        receta.getIngredientes().add(relacion);

        return recetaRepository.save(receta);
    }
}