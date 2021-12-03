package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
import com.venteEnLigne.venteEnLigne.model.view.SellerView;
import com.venteEnLigne.venteEnLigne.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @GetMapping("/initData")
    public ResponseEntity<HttpStatus>  initData() {
        return sellerService.initData();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus>  create(@RequestBody SellerEntity sellerEntity) {
        sellerService.create(sellerEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SellerEntity> update(@PathVariable("id") long id, @RequestBody SellerEntity sellerEntity) {
        return sellerService.update(id, sellerEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        return sellerService.delete(id);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<SellerView> getProduitById(@PathVariable("id") long id) {
        return sellerService.getSellerById(id).map(seller -> new ResponseEntity<>(seller, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SellerView>> finddAll() {
        return new ResponseEntity<>(sellerService.finddAll(), HttpStatus.OK);
    }

}