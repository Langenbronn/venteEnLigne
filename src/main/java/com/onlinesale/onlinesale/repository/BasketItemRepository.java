package com.onlinesale.onlinesale.repository;


import com.onlinesale.onlinesale.model.data.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BasketItemRepository extends JpaRepository<BasketItem, UUID> {
}