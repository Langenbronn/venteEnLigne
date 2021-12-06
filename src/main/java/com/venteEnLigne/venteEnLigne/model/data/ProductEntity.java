package com.venteEnLigne.venteEnLigne.model.data;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique=true)
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;
    @Column(name = "numberAvailable")
    private int numberAvailable;
    @JoinColumn(name = "seller")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<SellerEntity> sellerEntity;

    public ProductEntity() {
    }

    public ProductEntity(String name, double price, String description, int numberAvailable) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberAvailable = numberAvailable;
    }
}