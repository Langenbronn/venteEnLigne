package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.ProductEntity;
import com.venteEnLigne.venteEnLigne.model.view.ProductView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    //    @Mappings({
//            @Mapping(source = "sellersEntity", target = "sellersView"),
//    })
    ProductView entityToView(ProductEntity productEntity);

    //    @Mappings({
//            @Mapping(source = "sellersView", target = "sellersEntity"),
//    })
    ProductEntity viewToEntity(ProductView productView);
}