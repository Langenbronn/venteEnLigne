package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ProductView implements Serializable {
    private UUID id;

    private String name;
    private double price;
    private String description;

    public ProductView() {
    }

}