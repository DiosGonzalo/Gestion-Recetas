package com.salesianostriana.dam.apirecetas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Receta_Ingrediente {

    @EmbeddedId
    private Receta_Ingrediente_PK pk = new Receta_Ingrediente_PK();

    public Receta_Ingrediente(Ingrediente i, Receta r){
        this.ingrediente = i;
        this.receta = r;
    }


    @ManyToOne
    @MapsId("ingrediente_id")
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @ManyToOne
    @MapsId("receta_id")
    @JoinColumn(name = "receta_id")
    private Receta receta;

    private double cantidad;
    private String unidad;














}
