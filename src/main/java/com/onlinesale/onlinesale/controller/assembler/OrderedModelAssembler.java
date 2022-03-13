package com.onlinesale.onlinesale.controller.assembler;

import com.onlinesale.onlinesale.controller.OrderedController;
import com.onlinesale.onlinesale.model.view.OrdererView;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderedModelAssembler implements RepresentationModelAssembler<OrdererView, EntityModel<OrdererView>> {

    @Override
    public EntityModel<OrdererView> toModel(OrdererView ordererView) {

        return EntityModel.of(ordererView,
                linkTo(methodOn(OrderedController.class).findOne(ordererView.getId())).withSelfRel(),
                linkTo(methodOn(OrderedController.class).findAll()).withRel("ordererItems"));
    }
}