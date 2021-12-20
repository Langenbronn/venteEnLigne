package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.*;

@Data
public class ProductDto {
    private String name;
    private double price;
    private String categorie;
    private String description;
}