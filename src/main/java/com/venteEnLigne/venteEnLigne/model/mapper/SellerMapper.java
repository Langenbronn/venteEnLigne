package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.Seller;
import com.venteEnLigne.venteEnLigne.model.view.SellerView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper {

    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    SellerView entityToView(Seller seller);

    Seller viewToEntity(SellerView sellerView);
}