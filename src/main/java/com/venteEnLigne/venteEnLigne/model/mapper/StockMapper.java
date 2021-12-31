package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.StockEntity;
import com.venteEnLigne.venteEnLigne.model.view.StockView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

        @Mappings({
            @Mapping(source = "productEntity", target = "productView"),
                @Mapping(source = "sellerEntity", target = "sellerView")
    })
    StockView entityToView(StockEntity stockEntity);

    StockEntity viewToEntity(StockView stockView);
}