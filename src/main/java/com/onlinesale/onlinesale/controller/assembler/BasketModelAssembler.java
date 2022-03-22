package com.onlinesale.onlinesale.controller.assembler;

import com.onlinesale.onlinesale.controller.BasketController;
import com.onlinesale.onlinesale.controller.OrderedController;
import com.onlinesale.onlinesale.model.view.BasketView;
import com.onlinesale.onlinesale.model.view.OrdererView;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BasketModelAssembler implements RepresentationModelAssembler<BasketView, EntityModel<BasketView>> {

    @Override
    public EntityModel<BasketView> toModel(BasketView ordererView) {

        return EntityModel.of(ordererView,
                linkTo(methodOn(BasketController.class).findOne(ordererView.getId())).withSelfRel(),
                linkTo(methodOn(BasketController.class).findAll()).withRel("baskets"));
    }
}