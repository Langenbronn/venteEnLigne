package com.onlinesale.onlinesale.repository;


import com.onlinesale.onlinesale.model.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
}