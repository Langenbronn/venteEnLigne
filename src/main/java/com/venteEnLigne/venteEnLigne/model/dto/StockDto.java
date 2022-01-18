package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StockDto {
    private UUID idProduct;
    private UUID idSeller;
    private int quantity;
}
