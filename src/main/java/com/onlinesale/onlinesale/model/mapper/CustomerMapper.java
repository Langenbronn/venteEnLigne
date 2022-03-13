package com.onlinesale.onlinesale.model.mapper;

import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.model.dto.CustomerDto;
import com.onlinesale.onlinesale.model.view.CustomerView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerView entityToView(Customer customer);

    Customer DtoToEntity(CustomerDto customerDto);
}