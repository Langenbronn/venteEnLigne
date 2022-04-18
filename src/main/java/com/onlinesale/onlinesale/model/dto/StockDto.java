package com.onlinesale.onlinesale.model.dto;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
public class StockDto {
    private UUID idProduct;
    private UUID idSeller;
    @Positive
    private int quantity;
}
