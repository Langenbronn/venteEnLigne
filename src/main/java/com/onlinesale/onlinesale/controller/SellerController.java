package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.dto.SellerDto;
import com.onlinesale.onlinesale.model.mapper.SellerMapper;
import com.onlinesale.onlinesale.model.view.SellerView;
import com.onlinesale.onlinesale.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    SellerService sellerService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody SellerDto sellerDto) {
        SellerView sellerView = sellerService.create(sellerMapper.dtoToEntity(sellerDto));
        return new ResponseEntity<>(sellerView.getName() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody SellerDto sellerDto) {
        SellerView sellerView = sellerService.update(id, sellerMapper.dtoToEntity(sellerDto));
        return new ResponseEntity<>(sellerView.getName() + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        sellerService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerView> findOne(@PathVariable("id") UUID id) {
        return sellerService.findOne(id).map(seller -> new ResponseEntity<>(seller, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<SellerView>> findAll() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SellerView> findByName(@PathVariable("name") String name) {
        return sellerService.getByName(name).map(seller -> new ResponseEntity<>(seller, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}