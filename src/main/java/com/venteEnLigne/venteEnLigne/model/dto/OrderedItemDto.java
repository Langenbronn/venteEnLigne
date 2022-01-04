package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
public class OrderedItemDto implements Serializable {
    private int quantity;
    private double price;
    private Long idStock;
}