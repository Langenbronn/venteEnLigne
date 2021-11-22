package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.Product;
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
    public ResponseEntity<HttpStatus>  create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        return productService.delete(id);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Product> getProduitById(@PathVariable("id") long id) {
        return productService.getProduitById(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> finddAll() {
        return productService.finddAll();
    }

}