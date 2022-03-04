package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.BadRequestException;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.repository.ProductRepository;
import com.onlinesale.onlinesale.model.data.Product;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Data
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product create(Product product) throws IllegalStateException {
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new BadRequestException("product " + product.getName() + " does already exist");
        }
        return productRepository.save(product);
    }

    public Product update(UUID id, Product product) {
        productRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("product " + id + " does not exist"));

        product.setId(id);
        return productRepository.save(product);
    }

    public void delete(UUID id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("product " + id + " don't exist");
        }
        productRepository.deleteById(id);
    }

    public Optional<Product> findOne(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }
}
