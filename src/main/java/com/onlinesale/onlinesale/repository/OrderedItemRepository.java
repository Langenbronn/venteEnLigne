package com.onlinesale.onlinesale.repository;


import com.onlinesale.onlinesale.model.data.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderedItemRepository extends JpaRepository<OrderedItem, UUID> {
}