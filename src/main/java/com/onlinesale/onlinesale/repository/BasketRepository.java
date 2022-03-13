package com.onlinesale.onlinesale.repository;


import com.onlinesale.onlinesale.model.data.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BasketRepository extends JpaRepository<Basket, UUID> {
}