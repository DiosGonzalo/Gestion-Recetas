package com.salesianostriana.dam.apirecetas.services;


import com.salesianostriana.dam.apirecetas.Repository.CategoriaRepository;
import com.salesianostriana.dam.apirecetas.models.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public List<Categoria> getAll(){
        List<Categoria> lista = categoriaRepository.findAll();
        return lista;
    }
}
