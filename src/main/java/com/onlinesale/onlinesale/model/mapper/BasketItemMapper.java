package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.*;
import com.onlinesale.onlinesale.model.dto.BasketItemDto;
import com.onlinesale.onlinesale.model.dto.OrderedItemDto;
import com.onlinesale.onlinesale.model.view.BasketItemView;
import com.onlinesale.onlinesale.model.view.OrdererItemView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BasketItemMapper {

    BasketItemMapper INSTANCE = Mappers.getMapper(BasketItemMapper.class);

    @Mappings({
            @Mapping(source = "stock", target = "stockView"),
            @Mapping(source = "basket", target = "basketView")
    })
    BasketItemView entityToView(BasketItem basketItem);

    default BasketItem dtoToEntity(BasketItemDto basketItemDto) {
        BasketItem basketItem = new BasketItem();
        basketItem.setQuantity(basketItemDto.getQuantity());
        basketItem.setPrice(basketItemDto.getPrice());
        basketItem.setStock(new Stock(basketItemDto.getIdStock()));
        basketItem.setBasket(new Basket(basketItemDto.getIdBasket()));
        return basketItem;
    }

}