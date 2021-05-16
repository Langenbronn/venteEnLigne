package com.venteEnLigne.venteEnLigne.repository;

import com.venteEnLigne.venteEnLigne.model.ProduitEnVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitEnVenteRepository  extends JpaRepository<ProduitEnVente, Long> {
    List<ProduitEnVente> findByPublished(boolean published);
    List<ProduitEnVente> findByTitleContaining(String title);
}