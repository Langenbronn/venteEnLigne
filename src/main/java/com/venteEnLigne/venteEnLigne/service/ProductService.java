package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.data.ProductEntity;
import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
import com.venteEnLigne.venteEnLigne.model.mapper.ProductMapper;
import com.venteEnLigne.venteEnLigne.model.view.ProductView;
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
import java.util.stream.Collectors;


@Data
@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;

    public ResponseEntity<HttpStatus> initData() {
        productRepository.saveAll(Arrays.asList(new ProductEntity("Smartphone", 200.00, "Iphone 5", 5)
                , new ProductEntity("Calculette", 250, "XXX", 5)
                , new ProductEntity("Blouson", 100, "dddd", 5)
                , new ProductEntity("Canapé", 600.00, "zzzz", 5)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ProductView create(@RequestBody ProductEntity productEntity) throws IllegalStateException {

        if (productRepository.findByName(productEntity.getName()).isPresent()) {
            throw new IllegalStateException("product " + productEntity.getName() + " does already exist");
        }

        ProductEntity productData = productRepository.save(new ProductEntity(productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getDescription(),
                productEntity.getNumberAvailable()));
        return productMapper.entityToView(productData);
    }

    public ProductView update(long id, ProductEntity productEntity) {
        Optional<ProductEntity> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            ProductEntity _productEntity = productData.get();
            _productEntity.setId(productData.get().getId());
            _productEntity.setName(productEntity.getName());
            _productEntity.setPrice(productEntity.getPrice());
            _productEntity.setDescription(productEntity.getDescription());
            _productEntity.setNumberAvailable(productEntity.getNumberAvailable());
            productRepository.save(_productEntity);
            return productMapper.entityToView(_productEntity);
        } else {
            throw new IllegalStateException("product " + id + " don't exist");
        }
    }

    public void delete(long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new IllegalStateException("product " + id + " don't exist");
        }
        productRepository.deleteById(id);
    }

    public Optional<ProductView> getProduitById(long id) {
        Optional<ProductEntity> productData = productRepository.findById(id);
        return productData.map(product -> productMapper.entityToView(product));
    }

    public List<ProductView> finddAll() {
        List<ProductEntity> productEntityData = productRepository.findAll();
        return productEntityData.stream()
                .map(e -> productMapper.entityToView(e))
                .collect(Collectors.toList());
    }

    private SellerEntity toSeller(SellerEntity sellerEntity) throws IllegalStateException {
        return sellerRepository.findByName(sellerEntity.getName())
                .orElseThrow(() -> new IllegalStateException("seller " + sellerEntity.getName() + " does not exist"));
    }

}
