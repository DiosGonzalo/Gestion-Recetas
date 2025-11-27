package com.salesianostriana.dam.apirecetas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Receta_Ingrediente {

    @EmbeddedId
    private Receta_Ingrediente_PK pk = new Receta_Ingrediente_PK();

    public Receta_Ingrediente(Ingrediente i, Receta r){
        this.ingrediente = i;
        this.receta = r;
    }

//    @Id
//    @GeneratedValue
//    private Long id;
    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @ManyToOne
    @MapsId("recetaId")
    @JoinColumn(name = "receta_id")
    private Receta receta;

    private double cantidad;
    private String unidad;


    public void addToReceta(Receta r) {
        r.getIngredientes().add(this);
        this.receta = r;
    }

    public void removeFromReceta(Receta r) {
        r.getIngredientes().remove(this);
        this.receta = null;
    }













}
