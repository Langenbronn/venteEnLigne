package com.onlinesale.onlinesale.model.view;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class OrdererView implements Serializable {
    private UUID id;
    private CustomerView customerView;

    public OrdererView() {
    }

}