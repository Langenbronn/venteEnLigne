package com.onlinesale.onlinesale.controller.assembler;

import com.onlinesale.onlinesale.controller.OrderedItemController;
import com.onlinesale.onlinesale.controller.StockController;
import com.onlinesale.onlinesale.model.view.OrdererItemView;
import com.onlinesale.onlinesale.model.view.StockView;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderedItemModelAssembler implements RepresentationModelAssembler<OrdererItemView, EntityModel<OrdererItemView>> {

    @Override
    public EntityModel<OrdererItemView> toModel(OrdererItemView ordererItemView) {

        return EntityModel.of(ordererItemView,
                linkTo(methodOn(OrderedItemController.class).findOne(ordererItemView.getId())).withSelfRel(),
                linkTo(methodOn(OrderedItemController.class).findAll()).withRel("ordererItems"));
    }
}