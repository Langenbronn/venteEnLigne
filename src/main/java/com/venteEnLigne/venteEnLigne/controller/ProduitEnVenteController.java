package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.ProduitEnVente;
import com.venteEnLigne.venteEnLigne.repository.ProduitEnVenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produitEnVente")
public class ProduitEnVenteController {
    @Autowired
    ProduitEnVenteRepository produitEnVenteRepository;

    @GetMapping("/initData")
    public String initData() {
        produitEnVenteRepository.saveAll(Arrays.asList(new ProduitEnVente("Smartphone", 200.00, "Iphone 5", 5)
                , new ProduitEnVente("Calculette", 250, "XXX", 5)
                , new ProduitEnVente("Blouson", 100, "dddd", 5)
                , new ProduitEnVente("Canapé", 600.00, "zzzz", 5)));
        return "Customers are created";
    }

    @PostMapping("/create")
    public String create(@RequestBody ProduitEnVente produitEnVente) {
        produitEnVenteRepository.save(new ProduitEnVente(produitEnVente.getNom(),
                produitEnVente.getPrix(),
                produitEnVente.getDescription(),
                5));
        return "Customer is created";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProduitEnVente> update(@PathVariable("id") long id, @RequestBody ProduitEnVente produitEnVente) {
        Optional<ProduitEnVente> produitEnVenteData = produitEnVenteRepository.findById(id);

        if (produitEnVenteData.isPresent()) {
            ProduitEnVente _produitEnVente = produitEnVenteData.get();
            _produitEnVente.setId(produitEnVenteData.get().getId());
            _produitEnVente.setNom(produitEnVente.getNom());
            _produitEnVente.setPrix(produitEnVente.getPrix());
            _produitEnVente.setDescription(produitEnVente.getDescription());
            _produitEnVente.setNombreDisponible(produitEnVente.getNombreDisponible());
            return new ResponseEntity<>(produitEnVenteRepository.save(_produitEnVente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            produitEnVenteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProduitEnVente> getProduitById(@PathVariable("id") long id) {
        Optional<ProduitEnVente> produitEnVentelData = produitEnVenteRepository.findById(id);
        return produitEnVentelData.map(produitEnVente -> new ResponseEntity<>(produitEnVente, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProduitEnVente>> finddAll() {
        List<ProduitEnVente> produitEnVentelData = produitEnVenteRepository.findAll();
        return new ResponseEntity<>(produitEnVentelData, HttpStatus.OK);
    }

}