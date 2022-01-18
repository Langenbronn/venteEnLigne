package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.dto.ProductDto;
import com.venteEnLigne.venteEnLigne.model.view.ProductView;
import com.venteEnLigne.venteEnLigne.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/initData")
    public ResponseEntity<HttpStatus> initData() {
        return productService.initData();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ProductDto productDto) {
        ProductView productView = productService.create(productDto);
        return new ResponseEntity<>(productView.getName() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody ProductDto productDto) {
        ProductView productView = productService.update(id, productDto);
        return new ResponseEntity<>(productView.getName() + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        productService.delete(id);
        return new ResponseEntity<>(id + " has been deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProductView> getProduitById(@PathVariable("id") UUID id) {
        return productService.getProduitById(id).map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductView>> finddAll() {
        return new ResponseEntity<>(productService.finddAll(), HttpStatus.OK);
    }

}