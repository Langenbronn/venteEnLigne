package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.Seller;
import com.onlinesale.onlinesale.model.view.SellerView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper {

    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    SellerView entityToView(Seller seller);

    Seller viewToEntity(SellerView sellerView);
}