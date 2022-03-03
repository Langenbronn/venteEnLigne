package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.dto.OrderedItemDto;
import com.onlinesale.onlinesale.model.view.OrdererItemView;
import com.onlinesale.onlinesale.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orderedItems")
public class OrderedItemController {
    @Autowired
    OrderedItemService orderedItemService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody OrderedItemDto orderedItemDto) {
        OrdererItemView ordererItemView = orderedItemService.create(orderedItemDto);
        return new ResponseEntity<>(ordererItemView.getId() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody OrderedItemDto orderedItemDto) {
        OrdererItemView ordererItemView = orderedItemService.update(id, orderedItemDto);
        return new ResponseEntity<>(ordererItemView.getId() + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        orderedItemService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdererItemView> findOne(@PathVariable("id") UUID id) {
        return orderedItemService.findOne(id).map(stock -> new ResponseEntity<>(stock, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<OrdererItemView>> findAll() {
        return new ResponseEntity<>(orderedItemService.findAll(), HttpStatus.OK);
    }

}