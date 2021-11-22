package com.venteEnLigne.venteEnLigne.repository;

import com.venteEnLigne.venteEnLigne.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}