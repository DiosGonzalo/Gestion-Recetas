package com.salesianostriana.dam.apirecetas.services;

import ch.qos.logback.core.util.StringUtil;
import com.salesianostriana.dam.apirecetas.Repository.IngredienteRespository;
import com.salesianostriana.dam.apirecetas.errors.IngredienteAlredyAddedException;
import com.salesianostriana.dam.apirecetas.errors.TiempoInvalidoException;
import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import com.salesianostriana.dam.apirecetas.models.dto.IngredienteCmd;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredienteService {

    private final IngredienteRespository ingredienteRespository;

    public List<Ingrediente> getAll(){
        List<Ingrediente> lista = ingredienteRespository.findAll();
        return lista;
    }

    public Ingrediente saveIngrediente(IngredienteCmd cmd){
        if(!StringUtils.hasText(cmd.nombre()))
            throw new TiempoInvalidoException();

        if(ingredienteRespository.existsByNombre(cmd.nombre())){
            throw new IngredienteAlredyAddedException();
        }
        Ingrediente nuevo = cmd.toEntity();
        return ingredienteRespository.save(nuevo);
    }
}
