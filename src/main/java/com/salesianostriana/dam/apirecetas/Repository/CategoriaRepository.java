package com.salesianostriana.dam.apirecetas.repository;

import com.salesianostriana.dam.apirecetas.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);

    Optional<Categoria> findByNombre(String nombre);
}


