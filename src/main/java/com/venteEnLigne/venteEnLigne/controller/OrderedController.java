package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.dto.OrderedDto;
import com.venteEnLigne.venteEnLigne.model.view.OrdererView;
import com.venteEnLigne.venteEnLigne.service.OrderedService;
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

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody OrderedDto orderedDto) {
        OrdererView ordererView = orderedService.create(orderedDto);
        return new ResponseEntity<>(ordererView.getId() + " has been created", HttpStatus.CREATED);
    }

//    TODO fix
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody OrderedDto orderedItemDto) {
//        OrdererView ordererItemView = orderedService.update(id, orderedItemDto);
//        return new ResponseEntity<>(ordererItemView.getId() + " has been updated", HttpStatus.CREATED);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        orderedService.delete(id);
        return new ResponseEntity<>(id + " has been deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<OrdererView> getOrdererById(@PathVariable("id") UUID id) {
        return orderedService.getOrdererById(id).map(stock -> new ResponseEntity<>(stock, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OrdererView>> findAll() {
        return new ResponseEntity<>(orderedService.findAll(), HttpStatus.OK);
    }

}