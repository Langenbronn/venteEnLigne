package com.onlinesale.onlinesale.controller.assembler;

import com.onlinesale.onlinesale.controller.SellerController;
import com.onlinesale.onlinesale.model.view.SellerView;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SellerModelAssembler implements RepresentationModelAssembler<SellerView, EntityModel<SellerView>> {

    @Override
    public EntityModel<SellerView> toModel(SellerView sellerView) {

        return EntityModel.of(sellerView,
                linkTo(methodOn(SellerController.class).findOne(sellerView.getId())).withSelfRel(),
                linkTo(methodOn(SellerController.class).findAll()).withRel("sellers"));
    }
}