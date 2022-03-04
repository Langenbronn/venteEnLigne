package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.data.Seller;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    SellerService sellerService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody SellerDto sellerDto) {
        Seller seller = sellerService.create(sellerMapper.dtoToEntity(sellerDto));
        return new ResponseEntity<>(seller.getName() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody SellerDto sellerDto) {
        Seller seller = sellerService.update(id, sellerMapper.dtoToEntity(sellerDto));
        return new ResponseEntity<>(seller.getName() + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        sellerService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerView> findOne(@PathVariable("id") UUID id) {
        return sellerService.findOne(id).map(seller -> new ResponseEntity<>(sellerMapper.entityToView(seller), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<SellerView>> findAll() {
        List<SellerView> sellerViews = sellerService.findAll()
                .stream().map(seller -> sellerMapper.entityToView(seller))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sellerViews, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SellerView> findByName(@PathVariable("name") String name) {
        return sellerService.findByName(name).map(seller -> new ResponseEntity<>(sellerMapper.entityToView(seller), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}