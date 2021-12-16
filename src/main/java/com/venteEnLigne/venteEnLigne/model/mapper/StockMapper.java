package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.StockEntity;
import com.venteEnLigne.venteEnLigne.model.view.StockView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockView entityToView(StockEntity stockEntity);

    StockEntity viewToEntity(StockView stockView);
}