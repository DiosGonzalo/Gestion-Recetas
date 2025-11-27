package com.salesianostriana.dam.apirecetas.repository;

import com.salesianostriana.dam.apirecetas.models.Categoria;
import com.salesianostriana.dam.apirecetas.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    boolean existsByNombre(String nombre);

    Optional<Receta> findByNombre(String nombre);
}
