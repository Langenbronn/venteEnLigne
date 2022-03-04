package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.data.OrderedItem;
import com.onlinesale.onlinesale.model.dto.OrderedItemDto;
import com.onlinesale.onlinesale.model.mapper.OrdererItemMapper;
import com.onlinesale.onlinesale.model.view.OrdererItemView;
import com.onlinesale.onlinesale.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orderedItems")
public class OrderedItemController {
    @Autowired
    OrdererItemMapper ordererItemMapper;
    @Autowired
    OrderedItemService orderedItemService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody OrderedItemDto orderedItemDto) {
        OrderedItem orderedItem = orderedItemService.create(ordererItemMapper.dtoToEntity(orderedItemDto));
        return new ResponseEntity<>(orderedItem.getId() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody OrderedItemDto orderedItemDto) {
        OrderedItem orderedItem = orderedItemService.update(id, ordererItemMapper.dtoToEntity(orderedItemDto));
        return new ResponseEntity<>(orderedItem.getId() + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        orderedItemService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdererItemView> findOne(@PathVariable("id") UUID id) {
        return orderedItemService.findOne(id).map(orderedItem -> new ResponseEntity<>(ordererItemMapper.entityToView(orderedItem), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    public ResponseEntity<List<OrdererItemView>> findAll() {
        List<OrdererItemView> ordererItemViews = orderedItemService.findAll()
                .stream().map(orderedItem -> ordererItemMapper.entityToView(orderedItem))
                .collect(Collectors.toList());
        return new ResponseEntity<>(ordererItemViews, HttpStatus.OK);
    }

}