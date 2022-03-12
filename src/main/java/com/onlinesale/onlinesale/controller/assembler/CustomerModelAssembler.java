package com.onlinesale.onlinesale.controller.assembler;

import com.onlinesale.onlinesale.controller.CustomerController;
import com.onlinesale.onlinesale.controller.SellerController;
import com.onlinesale.onlinesale.model.view.CustomerView;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<CustomerView, EntityModel<CustomerView>> {

    @Override
    public EntityModel<CustomerView> toModel(CustomerView customerView) {

        return EntityModel.of(customerView,
                linkTo(methodOn(CustomerController.class).findOne(customerView.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).findAll()).withRel("customers"));
    }
}