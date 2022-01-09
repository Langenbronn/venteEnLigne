package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.dto.OrderedItemDto;
import com.venteEnLigne.venteEnLigne.model.view.OrdererItemView;
import com.venteEnLigne.venteEnLigne.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderedItems")
public class OrderedItemController {
    @Autowired
    OrderedItemService orderedItemService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody OrderedItemDto orderedItemDto) {
        OrdererItemView ordererItemView = orderedItemService.create(orderedItemDto);
        return new ResponseEntity<>(ordererItemView.getId() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody OrderedItemDto orderedItemDto) {
        OrdererItemView ordererItemView = orderedItemService.update(id, orderedItemDto);
        return new ResponseEntity<>(ordererItemView.getId() + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        orderedItemService.delete(id);
        return new ResponseEntity<>(id + " has been deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<OrdererItemView> getOrdererItemById(@PathVariable("id") long id) {
        return orderedItemService.getOrdererItemById(id).map(stock -> new ResponseEntity<>(stock, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OrdererItemView>> finddAll() {
        return new ResponseEntity<>(orderedItemService.finddAll(), HttpStatus.OK);
    }

}