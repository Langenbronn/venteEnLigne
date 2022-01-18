package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class OrderedItemDto implements Serializable {
    private int quantity;
    private double price;
    private UUID idStock;
}