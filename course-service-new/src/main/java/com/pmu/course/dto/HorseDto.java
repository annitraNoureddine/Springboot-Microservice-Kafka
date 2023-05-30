package com.pmu.course.dto;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

public class HorseDto implements Serializable {
   private String nom;

    private int numero;

    public HorseDto() {
    }

    public HorseDto(String nom, int numero) {
        this.nom = nom;
        this.numero = numero;
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

    @Override
    public String toString() {
        return "HorseDto{" +
                "nom='" + nom + '\'' +
                ", numero=" + numero +
                '}';
    }


}
