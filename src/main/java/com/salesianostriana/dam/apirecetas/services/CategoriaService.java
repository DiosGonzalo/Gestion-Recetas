package com.salesianostriana.dam.apirecetas.services;


import com.salesianostriana.dam.apirecetas.Repository.CategoriaRepository;
import com.salesianostriana.dam.apirecetas.errors.DuplicatedNameException.DuplicatedNameException;
import com.salesianostriana.dam.apirecetas.errors.NotFoundException.MyEntityNotFoundException;
import com.salesianostriana.dam.apirecetas.errors.TiempoInvalidoException;
import com.salesianostriana.dam.apirecetas.models.Categoria;
import com.salesianostriana.dam.apirecetas.models.dto.CrearCategoriaCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategoria(){
        List<Categoria> lista = categoriaRepository.findAll();
        return lista;
    }

    public Categoria getCategoriaById(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(()-> new MyEntityNotFoundException(id));
    }

    public Categoria saveCategoria(CrearCategoriaCmd cmd){
        if(!StringUtils.hasText(cmd.nombre())){
            throw new TiempoInvalidoException("El nombre no puede estar vacio");
        }
        if(!categoriaRepository.existsByNombre(cmd.nombre())){
            throw new DuplicatedNameException();
        }

        Categoria nueva = cmd.toEntity();
        return categoriaRepository.save(nueva);
    }

    public Categoria edit(CrearCategoriaCmd cdm, Long id){
        return categoriaRepository.findById(id)
                .map(categoria ->{
                    categoria.setNombre(cdm.nombre());
                    categoria.setDescripcion(cdm.descripcion());

                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new TiempoInvalidoException());
    }

    public void deleteById(Long id){
        categoriaRepository.deleteById(id);
    }
    public void delete (Categoria categoria){
        deleteById(categoria.getId());
    }
}
