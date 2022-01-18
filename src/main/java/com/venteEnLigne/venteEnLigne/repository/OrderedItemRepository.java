package com.venteEnLigne.venteEnLigne.repository;


import com.venteEnLigne.venteEnLigne.model.data.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderedItemRepository extends JpaRepository<OrderedItem, UUID> {
}