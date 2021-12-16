package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockView implements Serializable {
    private Long id;
    private int quantity;
//    private ProductEntity productEntity;
//    private SellerEntity sellerEntity;

    public StockView() {
    }

}