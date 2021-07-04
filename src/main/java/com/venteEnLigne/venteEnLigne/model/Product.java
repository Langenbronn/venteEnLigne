package com.venteenligne.venteenligne.model;

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

    public Product(String name, double price, String description, int numberAvailable) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberAvailable = numberAvailable;
    }
}