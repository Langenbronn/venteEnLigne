package com.onlinesale.onlinesale.controller.assembler;

import com.onlinesale.onlinesale.controller.StockController;
import com.onlinesale.onlinesale.model.view.StockView;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StockModelAssembler implements RepresentationModelAssembler<StockView, EntityModel<StockView>> {

    @Override
    public EntityModel<StockView> toModel(StockView stockView) {

        return EntityModel.of(stockView,
                linkTo(methodOn(StockController.class).findOne(stockView.getId())).withSelfRel(),
                linkTo(methodOn(StockController.class).findAll()).withRel("stocks"));
    }
}