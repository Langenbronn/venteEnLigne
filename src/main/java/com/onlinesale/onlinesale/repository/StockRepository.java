package com.onlinesale.onlinesale.repository;

import com.onlinesale.onlinesale.model.data.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface StockRepository extends JpaRepository<Stock, UUID> {
    Optional<Stock> findFirstBySellerIdAndProductId(UUID idSeller, UUID idProduct);

}