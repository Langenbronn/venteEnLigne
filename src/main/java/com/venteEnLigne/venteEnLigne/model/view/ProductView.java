package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductView implements Serializable {
    private Long id;

    private String name;
    private double price;
    private String description;

    public ProductView() {
    }

}