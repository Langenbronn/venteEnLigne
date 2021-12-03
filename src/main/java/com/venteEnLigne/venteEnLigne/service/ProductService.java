package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.data.ProductEntity;
import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
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
        SellerEntity sellerEntity = toSeller(new SellerEntity("Philibert"));
        productRepository.saveAll(Arrays.asList(new ProductEntity("Smartphone", 200.00, "Iphone 5", 5, sellerEntity)
                , new ProductEntity("Calculette", 250, "XXX", 5, sellerEntity)
                , new ProductEntity("Blouson", 100, "dddd", 5, sellerEntity)
                , new ProductEntity("Canapé", 600.00, "zzzz", 5, sellerEntity)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> create(@RequestBody ProductEntity productEntity) {
        SellerEntity sellerEntity = toSeller(productEntity.getSellerEntity());

        productRepository.save(new ProductEntity(productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getDescription(),
                productEntity.getNumberAvailable(),
                sellerEntity));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<ProductEntity> update(long id, ProductEntity productEntity) {
        Optional<ProductEntity> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            ProductEntity _productEntity = productData.get();
            _productEntity.setId(productData.get().getId());
            _productEntity.setName(productEntity.getName());
            _productEntity.setPrice(productEntity.getPrice());
            _productEntity.setDescription(productEntity.getDescription());
            _productEntity.setNumberAvailable(productEntity.getNumberAvailable());
            return new ResponseEntity<>(productRepository.save(_productEntity), HttpStatus.OK);
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

    public ResponseEntity<ProductEntity> getProduitById(long id) {
        Optional<ProductEntity> productData = productRepository.findById(id);
        return productData.map(productEntity -> new ResponseEntity<>(productEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<ProductEntity>> finddAll() {
        List<ProductEntity> productEntityData = productRepository.findAll();
        return new ResponseEntity<>(productEntityData, HttpStatus.OK);
    }

    private SellerEntity toSeller(SellerEntity sellerEntity) throws IllegalStateException {
        return sellerRepository.findByName(sellerEntity.getName())
                .orElseThrow(() -> new IllegalStateException(sellerEntity.getName() + " does not exist"));
    }

}
