package com.salesianostriana.dam.apirecetas.services;

import com.salesianostriana.dam.apirecetas.repository.IngredienteRespository;
import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredienteService {

    private final IngredienteRespository ingredienteRespository;

    public List<Ingrediente> getAll(){
        List<Ingrediente> lista = ingredienteRespository.findAll();
        return lista;
    }
}
