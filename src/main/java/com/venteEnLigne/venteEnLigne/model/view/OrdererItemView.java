package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdererItemView implements Serializable {
    private Long id;

    private int quantity;
    private double price;
    private StockView stockView;

    public OrdererItemView() {
    }

}