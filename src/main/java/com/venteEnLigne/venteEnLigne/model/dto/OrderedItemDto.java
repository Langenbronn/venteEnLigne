package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderedItemDto implements Serializable {
    private int quantity;
    private double price;
    private Long idStock;
}