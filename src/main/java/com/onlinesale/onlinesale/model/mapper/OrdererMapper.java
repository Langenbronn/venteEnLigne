package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.Ordered;
import com.onlinesale.onlinesale.model.view.OrdererView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrdererMapper {

    OrdererMapper INSTANCE = Mappers.getMapper(OrdererMapper.class);

    @Mappings({
            @Mapping(source = "customer", target = "customerView")
    })
    OrdererView entityToView(Ordered ordered);

    Ordered viewToEntity(OrdererView ordererView);
}