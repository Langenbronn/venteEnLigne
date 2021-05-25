package com.venteEnLigne.venteEnLigne.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "produitEnVente")
public class ProduitEnVente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom")
    private String nom;
    @Column(name = "prix")
    private double prix;
    @Column(name = "description")
    private String description;
    @Column(name = "nombreDisponible")
    private int nombreDisponible;

    public ProduitEnVente() {
    }

    public ProduitEnVente(String nom, double prix, String description, int nombreDisponible) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.nombreDisponible = nombreDisponible;
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

    public int getNombreDisponible() {
        return nombreDisponible;
    }

    public void setNombreDisponible(int nombreDisponible) {
        this.nombreDisponible = nombreDisponible;
    }

    @Override
    public String toString() {
        return "ProduitEnVente{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", nombreDisponible=" + nombreDisponible +
                '}';
    }
}