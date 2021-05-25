package com.venteEnLigne.venteEnLigne.repository;

import com.venteEnLigne.venteEnLigne.model.ProduitEnVente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProduitEnVenteRepository  extends JpaRepository<ProduitEnVente, Long> {
}