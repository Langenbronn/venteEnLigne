package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.OrderedItem;
import com.venteEnLigne.venteEnLigne.model.view.OrdererItemView;
import com.venteEnLigne.venteEnLigne.model.view.OrdererView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.core.Ordered;

@Mapper
public interface OrdererItemMapper {

    OrdererItemMapper INSTANCE = Mappers.getMapper(OrdererItemMapper.class);

    OrdererItemView entityToView(OrderedItem orderedItem);
    OrderedItem viewToEntity(OrdererItemView ordererItemView);
}