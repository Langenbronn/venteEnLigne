package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.Stock;
import com.onlinesale.onlinesale.model.view.StockView;
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