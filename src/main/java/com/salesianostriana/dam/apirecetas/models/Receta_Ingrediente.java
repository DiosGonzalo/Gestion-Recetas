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


    public void addToAlumno(Receta a) {
        a.getIngredientes().add(this);
        this.receta = a;
    }

    public void removeFromAlumno(Receta a) {
        a.getIngredientes().remove(this);
        this.receta = null;
    }













}
