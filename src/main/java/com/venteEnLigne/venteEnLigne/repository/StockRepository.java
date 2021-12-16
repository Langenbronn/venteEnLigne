package com.venteEnLigne.venteEnLigne.repository;

import com.venteEnLigne.venteEnLigne.model.data.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findFirstBySellerEntityIdAndProductEntityId(Long idSeller, Long idProduct);

}