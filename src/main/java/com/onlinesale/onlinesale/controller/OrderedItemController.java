package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.controller.assembler.OrderedItemModelAssembler;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.OrderedItem;
import com.onlinesale.onlinesale.model.dto.OrderedItemDto;
import com.onlinesale.onlinesale.model.mapper.OrdererItemMapper;
import com.onlinesale.onlinesale.model.view.OrdererItemView;
import com.onlinesale.onlinesale.service.OrderedItemService;
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
@RequestMapping("/api/orderedItems")
public class OrderedItemController {
    @Autowired
    OrdererItemMapper ordererItemMapper;
    @Autowired
    OrderedItemService orderedItemService;
    @Autowired
    OrderedItemModelAssembler orderedItemModelAssembler;

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<OrdererItemView>> create(@RequestBody OrderedItemDto orderedItemDto) {
        OrderedItem orderedItem = orderedItemService.create(ordererItemMapper.dtoToEntity(orderedItemDto));
        OrdererItemView ordererItemView = ordererItemMapper.entityToView(orderedItem);
        EntityModel<OrdererItemView> entityModel = orderedItemModelAssembler.toModel(ordererItemView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<OrdererItemView>> update(@PathVariable("id") UUID id, @RequestBody OrderedItemDto orderedItemDto) {
        OrderedItem orderedItem = orderedItemService.update(id, ordererItemMapper.dtoToEntity(orderedItemDto));
        OrdererItemView ordererItemView = ordererItemMapper.entityToView(orderedItem);
        EntityModel<OrdererItemView> entityModel = orderedItemModelAssembler.toModel(ordererItemView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        orderedItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public EntityModel<OrdererItemView> findOne(@PathVariable("id") UUID id) {
        return orderedItemService.findOne(id)
                .map(ordererItemMapper::entityToView)
                .map(orderedItemModelAssembler::toModel)
                .orElseThrow(() -> new NotFoundRequestException(" orderedItem " + id + " does not exist"));
    }


    @GetMapping
    public CollectionModel<EntityModel<OrdererItemView>> findAll() {
        List<EntityModel<OrdererItemView>> ordererItemViews = orderedItemService.findAll()
                .stream()
                .map(ordererItemMapper::entityToView)
                .map(orderedItemModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(ordererItemViews, linkTo(methodOn(SellerController.class).findAll()).withSelfRel());
    }

}