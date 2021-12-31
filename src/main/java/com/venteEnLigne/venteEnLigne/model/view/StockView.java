package com.venteEnLigne.venteEnLigne.model.view;

import com.venteEnLigne.venteEnLigne.model.data.ProductEntity;
import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class StockView implements Serializable {
    private Long id;
    private int quantity;
//    TODO
    private ProductView productView;
    private SellerView sellerView;

    public StockView() {
    }

}