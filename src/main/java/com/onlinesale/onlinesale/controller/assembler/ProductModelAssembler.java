package com.onlinesale.onlinesale.controller.assembler;

import com.onlinesale.onlinesale.controller.ProductController;
import com.onlinesale.onlinesale.model.view.ProductView;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<ProductView, EntityModel<ProductView>> {

    @Override
    public EntityModel<ProductView> toModel(ProductView productView) {

        return EntityModel.of(productView,
                linkTo(methodOn(ProductController.class).findOne(productView.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).findAll()).withRel("products"));
    }
}