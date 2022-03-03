package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.Basket;
import com.venteEnLigne.venteEnLigne.model.data.Ordered;
import com.venteEnLigne.venteEnLigne.model.view.BasketView;
import com.venteEnLigne.venteEnLigne.model.view.OrdererView;
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