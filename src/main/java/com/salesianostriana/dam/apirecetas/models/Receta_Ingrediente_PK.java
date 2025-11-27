package com.salesianostriana.dam.apirecetas.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Receta_Ingrediente_PK implements Serializable {

    private static final long serialVersionUID = 1L;

    private long recetaId;
    private long ingredienteId;
}
