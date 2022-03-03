package com.onlinesale.onlinesale.repository;

import com.onlinesale.onlinesale.model.data.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
    Optional<Seller> findByName(String name);
}