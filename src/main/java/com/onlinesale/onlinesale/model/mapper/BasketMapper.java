package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.Basket;
import com.onlinesale.onlinesale.model.view.BasketView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BasketMapper {

    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);

    @Mappings({
            @Mapping(source = "customer", target = "customerView")
    })
    BasketView entityToView(Basket basket);

    Basket viewToEntity(BasketView basketView);
}