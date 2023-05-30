package com.pmu.course.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "horse")
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horse_id")
    private Long horseId;

    @NotEmpty
    private String nom;

    private int numero;




    // Constructeur par défaut
    public Horse() {
    }



    // Constructeur avec paramètres


    public Horse(Long horseId, String nom, int numero) {
        this.horseId = horseId;
        this.nom = nom;
        this.numero = numero;
    }

    public Horse(String nom, int numero) {
        this.nom = nom;
        this.numero = numero;
    }

    // getters et setters


    public Long getHorseId() {
        return horseId;
    }

    public void setHorseId(Long horseId) {
        this.horseId = horseId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    //toString

    @Override
    public String toString() {
        return "Horse{" +
                "horseId=" + horseId +
                ", nom='" + nom + '\'' +
                ", numero=" + numero +
                '}';
    }
}
