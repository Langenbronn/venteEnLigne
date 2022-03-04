package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.data.Product;
import com.onlinesale.onlinesale.model.dto.ProductDto;
import com.onlinesale.onlinesale.model.mapper.ProductMapper;
import com.onlinesale.onlinesale.model.view.ProductView;
import com.onlinesale.onlinesale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductService productService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody ProductDto productDto) {
        Product product = productService.create(productMapper.dtoToEntity(productDto));
        return new ResponseEntity<>(product.getName() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody ProductDto productDto) {
        Product product = productService.update(id, productMapper.dtoToEntity(productDto));
        return new ResponseEntity<>(product.getName() + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductView> findOne(@PathVariable("id") UUID id) {
        return productService.findOne(id).map(product -> new ResponseEntity<>(productMapper.entityToView(product), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ProductView>> findAll() {
        List<ProductView> productViews = productService.findAll()
                .stream().map(product -> productMapper.entityToView(product))
                .collect(Collectors.toList());
        return new ResponseEntity<>(productViews, HttpStatus.OK);
    }

}