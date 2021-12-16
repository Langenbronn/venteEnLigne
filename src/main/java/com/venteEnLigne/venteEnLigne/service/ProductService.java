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
        ProductEntity productData = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("product " + id + " does not exist"));

        productData.setId(productData.getId());
            productData.setName(productEntity.getName());
            productData.setPrice(productEntity.getPrice());
            productData.setDescription(productEntity.getDescription());
            productData.setNumberAvailable(productEntity.getNumberAvailable());
            productRepository.save(productData);
            return productMapper.entityToView(productData);
    }

//    public ProductView addSeller(long idProduct, long idSeller) {
//        SellerEntity sellerEntity = sellerRepository.findById(idSeller)
//                .orElseThrow(() -> new IllegalStateException("seller " + idSeller + " does not exist"));
//
//        ProductEntity productEntity = productRepository.findById(idProduct)
//                .orElseThrow(() -> new IllegalStateException("product " + idProduct + " does not exist"));
//
//        if (productEntity.getSellersEntity().stream().anyMatch(seller -> seller.getId().equals(idSeller))) {
//            throw new IllegalStateException("seller " + idSeller + " already sell product " + idProduct);
//        }
//
//        productEntity.addSellerEntity(sellerEntity);
//        productRepository.save(productEntity);
//        return productMapper.entityToView(productEntity);
//    }

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
}
