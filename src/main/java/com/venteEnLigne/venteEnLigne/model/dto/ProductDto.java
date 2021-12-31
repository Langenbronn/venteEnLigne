package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private double price;
    private String categorie;
    private String description;
}