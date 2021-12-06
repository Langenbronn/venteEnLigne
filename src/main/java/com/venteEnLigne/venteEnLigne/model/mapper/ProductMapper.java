package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.ProductEntity;
import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
import com.venteEnLigne.venteEnLigne.model.view.ProductView;
import com.venteEnLigne.venteEnLigne.model.view.SellerView;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mappings({
            @Mapping(source = "sellerEntity", target = "sellerView"),
    })
    ProductView entityToView(ProductEntity productEntity);
    @Mappings({
            @Mapping(source = "sellerView", target = "sellerEntity"),
    })
    ProductEntity viewToEntity(ProductView productView);
}