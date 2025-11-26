package com.salesianostriana.dam.apirecetas.Repository;

import com.salesianostriana.dam.apirecetas.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
}
