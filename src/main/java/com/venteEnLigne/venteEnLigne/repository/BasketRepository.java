package com.venteEnLigne.venteEnLigne.repository;


import com.venteEnLigne.venteEnLigne.model.data.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BasketRepository extends JpaRepository<Basket, UUID> {
}