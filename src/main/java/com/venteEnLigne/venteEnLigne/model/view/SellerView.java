package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class SellerView implements Serializable {
    private Long id;
    private String name;

    public SellerView() {
    }

//    TODO
    public SellerView(String name) {
        this.name = name;
    }
}