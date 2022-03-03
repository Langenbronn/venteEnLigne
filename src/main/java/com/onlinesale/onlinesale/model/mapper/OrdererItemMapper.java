package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.OrderedItem;
import com.onlinesale.onlinesale.model.view.OrdererItemView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrdererItemMapper {

    OrdererItemMapper INSTANCE = Mappers.getMapper(OrdererItemMapper.class);

    @Mappings({
            @Mapping(source = "stock", target = "stockView"),
            @Mapping(source = "ordered", target = "ordererView")
    })
    OrdererItemView entityToView(OrderedItem orderedItem);

    OrderedItem viewToEntity(OrdererItemView ordererItemView);
}