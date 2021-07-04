package com.venteenligne.venteenligne.controller;

import com.venteenligne.venteenligne.model.Product;
import com.venteenligne.venteenligne.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/initData")
    public String initData() {
        return productService.initData();
    }

    @PostMapping("/create")
    public String create(@RequestBody Product product) {
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