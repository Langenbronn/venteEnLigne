package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.dto.OrderedDto;
import com.onlinesale.onlinesale.model.view.OrdererView;
import com.onlinesale.onlinesale.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ordereds")
public class OrderedController {
    @Autowired
    OrderedService orderedService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody OrderedDto orderedDto) {
        OrdererView ordererView = orderedService.create(orderedDto);
        return new ResponseEntity<>(ordererView.getId() + " has been created", HttpStatus.CREATED);
    }

//    TODO fix
//    @PutMapping("/{id}")
//    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody OrderedDto orderedItemDto) {
//        OrdererView ordererItemView = orderedService.update(id, orderedItemDto);
//        return new ResponseEntity<>(ordererItemView.getId() + " has been updated", HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        orderedService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdererView> findOne(@PathVariable("id") UUID id) {
        return orderedService.findOne(id).map(stock -> new ResponseEntity<>(stock, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<OrdererView>> findAll() {
        return new ResponseEntity<>(orderedService.findAll(), HttpStatus.OK);
    }

}