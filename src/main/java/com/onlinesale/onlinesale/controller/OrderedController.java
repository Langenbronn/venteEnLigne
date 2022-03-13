package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.controller.assembler.OrderedModelAssembler;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Ordered;
import com.onlinesale.onlinesale.model.dto.OrderedDto;
import com.onlinesale.onlinesale.model.mapper.OrdererMapper;
import com.onlinesale.onlinesale.model.view.OrdererView;
import com.onlinesale.onlinesale.service.OrderedService;
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
@RequestMapping("/api/ordereds")
public class OrderedController {
    @Autowired
    OrdererMapper ordererMapper;
    @Autowired
    OrderedService orderedService;
    @Autowired
    OrderedModelAssembler orderedModelAssembler;

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<OrdererView>> create(@RequestBody OrderedDto orderedDto) {
        Ordered ordered = orderedService.create(ordererMapper.dtoToEntity(orderedDto));
        OrdererView ordererItemView = ordererMapper.entityToView(ordered);
        EntityModel<OrdererView> entityModel = orderedModelAssembler.toModel(ordererItemView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

//    TODO fix
//    @PutMapping("/{id}")
//    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody OrderedDto orderedItemDto) {
//        OrdererView ordererItemView = orderedService.update(id, orderedItemDto);
//        return new ResponseEntity<>(ordererItemView.getId() + " has been updated", HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        orderedService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public EntityModel<OrdererView> findOne(@PathVariable("id") UUID id) {
        return orderedService.findOne(id)
                .map(ordererMapper::entityToView)
                .map(orderedModelAssembler::toModel)
                .orElseThrow(() -> new NotFoundRequestException(" ordered " + id + " does not exist"));

    }

    @GetMapping
    public CollectionModel<EntityModel<OrdererView>> findAll() {
        List<EntityModel<OrdererView>> ordererViews = orderedService.findAll()
                .stream()
                .map(ordererMapper::entityToView)
                .map(orderedModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(ordererViews, linkTo(methodOn(SellerController.class).findAll()).withSelfRel());
    }

}