package com.onlinesale.onlinesale.model.view;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class OrdererItemView implements Serializable {
    private UUID id;

    private int quantity;
    private double price;
    private StockView stockView;
    private OrdererView ordererView;

    public OrdererItemView() {
    }

}