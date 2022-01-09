package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.Stock;
import com.venteEnLigne.venteEnLigne.model.view.StockView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    @Mappings({
            @Mapping(source = "product", target = "productView"),
            @Mapping(source = "seller", target = "sellerView")
    })
    StockView entityToView(Stock stock);

    Stock viewToEntity(StockView stockView);
}