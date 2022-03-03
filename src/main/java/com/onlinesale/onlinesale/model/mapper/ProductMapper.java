package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.Product;
import com.onlinesale.onlinesale.model.view.ProductView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductView entityToView(Product product);

    Product viewToEntity(ProductView productView);
}