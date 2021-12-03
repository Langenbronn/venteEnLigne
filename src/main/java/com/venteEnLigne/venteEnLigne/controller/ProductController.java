package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.data.ProductEntity;
import com.venteEnLigne.venteEnLigne.model.view.ProductView;
import com.venteEnLigne.venteEnLigne.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/initData")
    public ResponseEntity<HttpStatus>  initData() {
        return productService.initData();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus>  create(@RequestBody ProductEntity productEntity) {
        return productService.create(productEntity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable("id") long id, @RequestBody ProductEntity productEntity) {
        return productService.update(id, productEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        return productService.delete(id);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProductEntity> getProduitById(@PathVariable("id") long id) {
        return productService.getProduitById(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductView>> finddAll() {
        return new ResponseEntity<>(productService.finddAll(), HttpStatus.OK);
    }

}