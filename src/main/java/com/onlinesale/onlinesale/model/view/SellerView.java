package com.onlinesale.onlinesale.model.view;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class SellerView implements Serializable {
    private UUID id;
    private String name;

    public SellerView() {
    }

}