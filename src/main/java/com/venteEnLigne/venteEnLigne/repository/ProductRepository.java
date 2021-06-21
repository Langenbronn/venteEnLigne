package com.venteenligne.venteenligne.repository;

import com.venteenligne.venteenligne.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<ProduitEnVente> findByPublished(boolean published);
}