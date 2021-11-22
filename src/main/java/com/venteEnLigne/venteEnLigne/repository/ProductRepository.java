package com.venteEnLigne.venteEnLigne.repository;


import com.venteEnLigne.venteEnLigne.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<ProduitEnVente> findByPublished(boolean published);
}