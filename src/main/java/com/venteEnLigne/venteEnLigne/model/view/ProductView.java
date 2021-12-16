package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductView implements Serializable {
    private Long id;

    private String name;
    private double price;
    private String description;
    private int numberAvailable;
//    private List<SellerView> sellersView;

    public ProductView() {
    }

    public ProductView(String name, double price, String description, int numberAvailable) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberAvailable = numberAvailable;
    }
}