package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.Product;
import com.venteEnLigne.venteEnLigne.model.Seller;
import com.venteEnLigne.venteEnLigne.repository.ProductRepository;
import com.venteEnLigne.venteEnLigne.repository.SellerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Data
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;

    public ResponseEntity<HttpStatus> initData() {
        Seller seller = toSeller(new Seller("Philibert"));
        productRepository.saveAll(Arrays.asList(new Product("Smartphone", 200.00, "Iphone 5", 5, seller)
                , new Product("Calculette", 250, "XXX", 5, seller)
                , new Product("Blouson", 100, "dddd", 5, seller)
                , new Product("Canapé", 600.00, "zzzz", 5, seller)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> create(@RequestBody Product product) {
        Seller seller = toSeller(product.getSeller());

        productRepository.save(new Product(product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getNumberAvailable(),
                seller));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Product> update(long id, Product product) {
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

    public ResponseEntity<HttpStatus> delete(long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Product> getProduitById(long id) {
        Optional<Product> productData = productRepository.findById(id);
        return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Product>> finddAll() {
        List<Product> productData = productRepository.findAll();
        return new ResponseEntity<>(productData, HttpStatus.OK);
    }

    private Seller toSeller(Seller seller) throws IllegalStateException {
        return sellerRepository.findByName(seller.getName())
                .orElseThrow(() -> new IllegalStateException(seller.getName() + " does not exist"));
    }

}
