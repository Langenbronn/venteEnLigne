package com.venteenligne.venteenligne.controller;

import com.venteenligne.venteenligne.model.Product;
import com.venteenligne.venteenligne.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/initData")
    public String initData() {
        productRepository.saveAll(Arrays.asList(new Product("Smartphone", 200.00, "Iphone 5", 5)
                , new Product("Calculette", 250, "XXX", 5)
                , new Product("Blouson", 100, "dddd", 5)
                , new Product("Canapé", 600.00, "zzzz", 5)));
        return "Customers are created";
    }

    @PostMapping("/create")
    public String create(@RequestBody Product product) {
        productRepository.save(new Product(product.getName(),
                product.getPrice(),
                product.getDescription(),
                5));
        return "Customer is created";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") long id, @RequestBody Product product) {
        Optional<Product> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setId(productData.get().getId());
            _product.setName(product.getName());
            _product.setPrice(product.getPrice());
            _product.setDescription(product.getDescription());
            _product.setNumberAvailable(product.getNumberAvailable());
            return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Product> getProduitById(@PathVariable("id") long id) {
        Optional<Product> productData = productRepository.findById(id);
        return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> finddAll() {
        List<Product> productData = productRepository.findAll();
        return new ResponseEntity<>(productData, HttpStatus.OK);
    }

}