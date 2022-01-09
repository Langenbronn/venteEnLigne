package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrdererView implements Serializable {
    private Long id;

    private List<OrdererItemView> ordererItemViews;

    public OrdererView() {
    }

}