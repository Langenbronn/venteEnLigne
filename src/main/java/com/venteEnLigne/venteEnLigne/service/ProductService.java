package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.data.ProductEntity;
import com.venteEnLigne.venteEnLigne.model.dto.ProductDto;
import com.venteEnLigne.venteEnLigne.model.mapper.ProductMapper;
import com.venteEnLigne.venteEnLigne.model.view.ProductView;
import com.venteEnLigne.venteEnLigne.repository.ProductRepository;
import com.venteEnLigne.venteEnLigne.repository.SellerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        productRepository.saveAll(Arrays.asList(new ProductEntity("Unlock ! Game Adventures", 30.71, "Jeux", "Dans Unlock! Games Adventures, plongez dans l'univers de Mysterium, Aventuriers du Rail et Pandemic")
                , new ProductEntity("7 Wonders : Architects", 35.00, "Jeux", "7 Wonders Architects est un nouveau jeu dans le monde de 7 Wonders. ")
                , new ProductEntity("Thorgun", 2.99, "Inconnu", "Plaid, gris-vert clair120x160 cm")
                , new ProductEntity("GODMORGON / ODENSVIK", 559.00, "Meuble", "Meuble lavabo 4tir, effet chêne blanchi/Dalskär mitigeur lavabo123x49x64 cm")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ProductView create(@RequestBody ProductDto productEntity) throws IllegalStateException {

        if (productRepository.findByName(productEntity.getName()).isPresent()) {
            throw new IllegalStateException("product " + productEntity.getName() + " does already exist");
        }

        ProductEntity productData = productRepository.save(new ProductEntity(productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getCategorie(),
                productEntity.getDescription()));
        return productMapper.entityToView(productData);
    }

    public ProductView update(long id, ProductDto productEntity) {
        ProductEntity productData = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("product " + id + " does not exist"));

        productData.setId(productData.getId());
        productData.setName(productEntity.getName());
        productData.setPrice(productEntity.getPrice());
        productData.setDescription(productEntity.getDescription());
        productRepository.save(productData);
        return productMapper.entityToView(productData);
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
}
