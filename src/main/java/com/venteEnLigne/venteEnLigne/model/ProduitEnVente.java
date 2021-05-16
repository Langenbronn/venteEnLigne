package com.venteEnLigne.venteEnLigne.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "produitEnVente")
public class ProduitEnVente implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private double prix;

    private String description;

//    private Marque marque;

    private int nombreDisponible;

    public ProduitEnVente(String nom, double prix, String description, int i) {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Marque getMarque() {
//        return marque;
//    }
//
//    public void setMarque(Marque marque) {
//        this.marque = marque;
//    }

    public int getNombreDisponible() {
        return nombreDisponible;
    }

    public void setNombreDisponible(int nombreDisponible) {
        this.nombreDisponible = nombreDisponible;
    }

}