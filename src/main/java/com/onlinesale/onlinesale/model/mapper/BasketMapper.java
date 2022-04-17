package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.Basket;
import com.onlinesale.onlinesale.model.data.BasketItem;
import com.onlinesale.onlinesale.model.dto.BasketDto;
import com.onlinesale.onlinesale.model.view.BasketView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface BasketMapper {

    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);

    @Mappings({
            @Mapping(source = "customer", target = "customerView"),
            @Mapping(source = "basketItems", target = "basketItemViews")
    })
    BasketView entityToView(Basket basket);

//    @Mappings({
            @Mapping(source = "basketItems", target = "basketItems", qualifiedByName = "basketItemsMapping")
//    })
    Basket dtoToEntity(BasketDto basketDto);

    @Named("basketItemsMapping")
    static Set<BasketItem> basketItemsMapping(Set<UUID> basketItems) {
        return basketItems.stream().map(BasketItem::new)
                .collect(Collectors.toSet());
    }


}