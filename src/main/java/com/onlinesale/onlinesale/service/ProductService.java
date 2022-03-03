package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.BadRequestException;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.repository.ProductRepository;
import com.onlinesale.onlinesale.repository.SellerRepository;
import com.onlinesale.onlinesale.model.data.Product;
import com.onlinesale.onlinesale.model.dto.ProductDto;
import com.onlinesale.onlinesale.model.mapper.ProductMapper;
import com.onlinesale.onlinesale.model.view.ProductView;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    public ProductView create(ProductDto productDto) throws IllegalStateException {
        if (productRepository.findByName(productDto.getName()).isPresent()) {
            throw new BadRequestException("product " + productDto.getName() + " does already exist");
        }

        Product product = productRepository.save(new Product(productDto.getName(),
                productDto.getPrice(),
                productDto.getCategorie(),
                productDto.getDescription()));
        return productMapper.entityToView(product);
    }

    public ProductView update(UUID id, ProductDto productDto) {
        Product productData = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("product " + id + " does not exist"));

        productData.setId(productData.getId());
        productData.setName(productDto.getName());
        productData.setPrice(productDto.getPrice());
        productData.setDescription(productDto.getDescription());
        productRepository.save(productData);
        return productMapper.entityToView(productData);
    }

    public void delete(UUID id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("product " + id + " don't exist");
        }
        productRepository.deleteById(id);
    }

    public Optional<ProductView> getProduitById(UUID id) {
        Optional<Product> productData = productRepository.findById(id);
        return productData.map(product -> productMapper.entityToView(product));
    }

    public List<ProductView> findAll() {
        List<Product> productData = productRepository.findAll();
        return productData.stream()
                .map(e -> productMapper.entityToView(e))
                .collect(Collectors.toList());
    }
}
