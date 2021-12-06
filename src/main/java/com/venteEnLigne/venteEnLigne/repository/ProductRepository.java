package com.venteEnLigne.venteEnLigne.repository;


import com.venteEnLigne.venteEnLigne.model.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);
}