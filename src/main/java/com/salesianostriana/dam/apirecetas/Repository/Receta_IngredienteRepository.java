package com.salesianostriana.dam.apirecetas.Repository;

import com.salesianostriana.dam.apirecetas.models.Receta_Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Receta_IngredienteRepository extends JpaRepository<Receta_Ingrediente,Long> {
}
