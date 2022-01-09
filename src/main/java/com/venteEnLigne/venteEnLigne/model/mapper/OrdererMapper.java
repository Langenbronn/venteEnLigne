package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.Ordered;
import com.venteEnLigne.venteEnLigne.model.view.OrdererView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrdererMapper {

    OrdererMapper INSTANCE = Mappers.getMapper(OrdererMapper.class);

    OrdererView entityToView(Ordered ordered);

    Ordered viewToEntity(OrdererView ordererView);
}