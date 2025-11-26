package com.salesianostriana.dam.apirecetas.repository;

import com.salesianostriana.dam.apirecetas.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRespository extends JpaRepository<Ingrediente,Long> {
}
