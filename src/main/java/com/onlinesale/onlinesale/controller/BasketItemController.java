package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.controller.assembler.BasketItemModelAssembler;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.BasketItem;
import com.onlinesale.onlinesale.model.data.OrderedItem;
import com.onlinesale.onlinesale.model.dto.BasketItemDto;
import com.onlinesale.onlinesale.model.dto.OrderedItemDto;
import com.onlinesale.onlinesale.model.mapper.BasketItemMapper;
import com.onlinesale.onlinesale.model.view.BasketItemView;
import com.onlinesale.onlinesale.model.view.OrdererItemView;
import com.onlinesale.onlinesale.service.BasketItemService;
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
@RequestMapping("/api/basketItems")
public class BasketItemController {
    @Autowired
    BasketItemMapper basketItemMapper;
    @Autowired
    BasketItemService basketItemService;
    @Autowired
    BasketItemModelAssembler basketItemModelAssembler;

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<BasketItemView>> create(@RequestBody BasketItemDto basketItemDto) {
        BasketItem basketItem = basketItemService.create(basketItemMapper.dtoToEntity(basketItemDto));
        BasketItemView basketItemView = basketItemMapper.entityToView(basketItem);
        EntityModel<BasketItemView> entityModel = basketItemModelAssembler.toModel(basketItemView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BasketItemView>> update(@PathVariable("id") UUID id, @RequestBody BasketItemDto basketItemDto) {
        BasketItem basketItem = basketItemService.update(id, basketItemMapper.dtoToEntity(basketItemDto));
        BasketItemView ordererItemView = basketItemMapper.entityToView(basketItem);
        EntityModel<BasketItemView> entityModel = basketItemModelAssembler.toModel(ordererItemView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        basketItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public EntityModel<BasketItemView> findOne(@PathVariable("id") UUID id) {
        return basketItemService.findOne(id)
                .map(basketItemMapper::entityToView)
                .map(basketItemModelAssembler::toModel)
                .orElseThrow(() -> new NotFoundRequestException(" basketItem " + id + " does not exist"));
    }


    @GetMapping
    public CollectionModel<EntityModel<BasketItemView>> findAll() {
        List<EntityModel<BasketItemView>> basketItemViews = basketItemService.findAll()
                .stream()
                .map(basketItemMapper::entityToView)
                .map(basketItemModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(basketItemViews, linkTo(methodOn(BasketItemController.class).findAll()).withSelfRel());
    }

}