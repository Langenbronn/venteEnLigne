package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class StockView implements Serializable {
    private UUID id;
    private int quantity;
    private ProductView productView;
    private SellerView sellerView;

    public StockView() {
    }

}