package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

@Data
public class StockDto {
    private Long idProduct;
    private Long idSeller;
    private int quantity;
}
