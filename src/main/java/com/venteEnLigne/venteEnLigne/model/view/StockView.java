package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockView implements Serializable {
    private Long id;
    private int quantity;
    private ProductView productView;
    private SellerView sellerView;

    public StockView() {
    }

}