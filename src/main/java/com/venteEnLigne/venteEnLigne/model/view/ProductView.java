package com.venteEnLigne.venteEnLigne.model.view;

import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class ProductView implements Serializable {
    private Long id;

    private String name;
    private double price;
    private String description;
    private int numberAvailable;
    private SellerView sellerView;

    public ProductView() {
    }

    public ProductView(String name, double price, String description, int numberAvailable, SellerView sellerView) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberAvailable = numberAvailable;
        this.sellerView = sellerView;
    }
}