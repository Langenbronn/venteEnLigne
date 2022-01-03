package com.venteEnLigne.venteEnLigne.model.mapper;

import com.venteEnLigne.venteEnLigne.model.data.Customer;
import com.venteEnLigne.venteEnLigne.model.data.Product;
import com.venteEnLigne.venteEnLigne.model.view.CustomerView;
import com.venteEnLigne.venteEnLigne.model.view.ProductView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerView entityToView(Customer customer);
    Customer viewToEntity(CustomerView customerView);
}