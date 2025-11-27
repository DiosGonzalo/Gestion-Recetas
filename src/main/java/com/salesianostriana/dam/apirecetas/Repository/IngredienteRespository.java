package com.salesianostriana.dam.apirecetas.Repository;

import com.salesianostriana.dam.apirecetas.models.Categoria;
import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredienteRespository extends JpaRepository<Ingrediente,Long> {
    boolean existsByNombre(String nombre);

    Optional<Ingrediente> findByNombre(String nombre);
}
