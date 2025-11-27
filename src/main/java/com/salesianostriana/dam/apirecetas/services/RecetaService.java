package com.salesianostriana.dam.apirecetas.services;


import com.salesianostriana.dam.apirecetas.errors.DuplicatedNameException.DuplicatedNameException;
import com.salesianostriana.dam.apirecetas.errors.TiempoInvalidoException;
import com.salesianostriana.dam.apirecetas.models.Receta;
import com.salesianostriana.dam.apirecetas.models.dto.IngredienteInReceta;
import com.salesianostriana.dam.apirecetas.models.dto.RecetaCmd;
import com.salesianostriana.dam.apirecetas.models.dto.RecetaResponse;
import com.salesianostriana.dam.apirecetas.repository.RecetaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class RecetaService {
    RecetaRepository recetaRepository;
    public List<RecetaResponse> getAll(){
        return recetaRepository.findAll()
                .stream()
                .map(receta -> RecetaResponse.of(receta))
                .toList();
    }

    public Receta getById(Long id){
        return recetaRepository.findById(id)
                .orElseThrow(() -> new TiempoInvalidoException(id));
    }

    public Receta create(RecetaCmd cmd){
        if(!StringUtils.hasText(cmd.nombre())){
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
                .orElseThrow(() -> new TiempoInvalidoException());
    }

    public void borrar(Long id){
        recetaRepository.delete(recetaRepository.findById(id)
                .orElseThrow(() -> new TiempoInvalidoException()));
    }

    public IngredienteInReceta recetaConIngredientes(Long id){
            Receta receta = recetaRepository.findById(id).orElseThrow(() -> new TiempoInvalidoException());
            return IngredienteInReceta.of(receta);
        }

}
