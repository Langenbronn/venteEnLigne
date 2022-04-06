package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.controller.assembler.BasketModelAssembler;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Basket;
import com.onlinesale.onlinesale.model.dto.BasketDto;
import com.onlinesale.onlinesale.model.mapper.BasketMapper;
import com.onlinesale.onlinesale.model.view.BasketView;
import com.onlinesale.onlinesale.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {
    @Autowired
    BasketMapper basketMapper;
    @Autowired
    BasketService basketService;
    @Autowired
    BasketModelAssembler basketModelAssembler;

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<BasketView>> create(@RequestBody BasketDto basketDto) {
        Basket basket = basketService.create(basketMapper.dtoToEntity(basketDto));
        BasketView basketView = basketMapper.entityToView(basket);
        EntityModel<BasketView> entityModel = basketModelAssembler.toModel(basketView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}/orderer")
    public ResponseEntity<?> update(@PathVariable("id") UUID id) {
        basketService.updateToOrderer(id);
        return ResponseEntity.noContent().build();
//        BasketView basketView = basketMapper.entityToView(basket);
//        EntityModel<BasketView> entityModel = basketModelAssembler.toModel(basketView);
//
//        return ResponseEntity
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        basketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public EntityModel<BasketView> findOne(@PathVariable("id") UUID id) {
        return basketService.findOne(id)
                .map(basketMapper::entityToView)
                .map(basketModelAssembler::toModel)
                .orElseThrow(() -> new NotFoundRequestException("basket " + id + " does not exist"));

    }

    @GetMapping
    public CollectionModel<EntityModel<BasketView>> findAll() {
        List<EntityModel<BasketView>> basketViews = basketService.findAll()
                .stream()
                .map(basketMapper::entityToView)
                .map(basketModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(basketViews, linkTo(methodOn(BasketController.class).findAll()).withSelfRel());
    }

}