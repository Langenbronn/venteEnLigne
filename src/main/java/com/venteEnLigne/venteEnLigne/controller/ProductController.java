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
    public ResponseEntity<HttpStatus> initData() {
        return productService.initData();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ProductEntity productEntity) {
        try {
            ProductView productView = productService.create(productEntity);
            return new ResponseEntity<>(productView.getName() + " has been created", HttpStatus.CREATED);
        } catch (IllegalStateException ise) {
            return new ResponseEntity<>(ise.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody ProductEntity productEntity) {
        try {
            ProductView productView = productService.update(id, productEntity);
            return new ResponseEntity<>(productView.getName() + " has been updated", HttpStatus.OK);
        } catch (IllegalStateException ise) {
            return new ResponseEntity<>(ise.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addSeller/{id}")
    public ResponseEntity<String> addSeller(@PathVariable("id") long id, @RequestBody long idSeller) {
        try {
            ProductView productView = productService.addSeller(id, idSeller);
            return new ResponseEntity<>("seller " + idSeller + " has been add", HttpStatus.OK);
        } catch (IllegalStateException ise) {
            return new ResponseEntity<>(ise.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        try {
            productService.delete(id);
            return new ResponseEntity<>(id + " has been deleted", HttpStatus.NO_CONTENT);
        } catch (IllegalStateException ise) {
            return new ResponseEntity<>(ise.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProductView> getProduitById(@PathVariable("id") long id) {
        return productService.getProduitById(id).map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductView>> finddAll() {
        return new ResponseEntity<>(productService.finddAll(), HttpStatus.OK);
    }

}