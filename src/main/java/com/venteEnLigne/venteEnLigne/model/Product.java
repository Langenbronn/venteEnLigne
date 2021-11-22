package com.venteEnLigne.venteEnLigne.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;
    @Column(name = "numberAvailable")
    private int numberAvailable;
    @JoinColumn(name = "seller", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Seller seller;

    public Product() {
    }

    public Product(String name, double price, String description, int numberAvailable, Seller seller) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberAvailable = numberAvailable;
        this.seller = seller;
    }
}