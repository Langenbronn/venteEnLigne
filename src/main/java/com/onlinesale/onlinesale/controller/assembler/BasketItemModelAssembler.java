package com.onlinesale.onlinesale.controller.assembler;

import com.onlinesale.onlinesale.controller.BasketItemController;
import com.onlinesale.onlinesale.controller.OrderedItemController;
import com.onlinesale.onlinesale.model.view.BasketItemView;
import com.onlinesale.onlinesale.model.view.OrdererItemView;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BasketItemModelAssembler implements RepresentationModelAssembler<BasketItemView, EntityModel<BasketItemView>> {

    @Override
    public EntityModel<BasketItemView> toModel(BasketItemView basketItemView) {

        return EntityModel.of(basketItemView,
                linkTo(methodOn(BasketItemController.class).findOne(basketItemView.getId())).withSelfRel(),
                linkTo(methodOn(BasketItemController.class).findAll()).withRel("basketItems"));
    }
}