package com.venteEnLigne.venteEnLigne.model.dto.stock;

import lombok.Data;

@Data
public class StockCreationDto {
    private Long idProduct;
    private Long idSeller;
    private int quantity;
}
