package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.ProduitEnVente;
import com.venteEnLigne.venteEnLigne.repository.ProduitEnVenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProduitEnVenteController  {
    @Autowired
    ProduitEnVenteRepository produitEnVenteRepository;

    @GetMapping("/initData")
    public String initData(){
// save a single Customer
        produitEnVenteRepository.save(new ProduitEnVente("Smartphone",200.00, "Iphone 5", 5));

// save a list of Customers
        produitEnVenteRepository.saveAll(Arrays.asList(new ProduitEnVente("Smartphone",200.00, "Iphone 5", 5)
                , new ProduitEnVente("Calculette",250, "XXX", 5)
                , new ProduitEnVente("Blouson",100, "dddd", 5)
                , new ProduitEnVente("Canapé",600.00, "zzzz", 5)));

        return "Customers are created";
    }
    @PostMapping("/create")
    public String create(@RequestBody ProduitEnVente produitEnVente){
// save a single Customer
        produitEnVenteRepository.save(new ProduitEnVente(produitEnVente.getNom(),
                produitEnVente.getPrix(),
                produitEnVente.getDescription(),
//                produitEnVente.getMarque(),
                5));

        return "Customer is created";
    }
    @GetMapping("/findall")
    public List<ProduitEnVente> findAll(){

        List<ProduitEnVente> listProduitEnVente = produitEnVenteRepository.findAll();
        List<ProduitEnVente> produitEnVenteOut = new ArrayList<>();

        for (ProduitEnVente produitEnVente : listProduitEnVente) {
            produitEnVenteOut.add(new ProduitEnVente(produitEnVente.getNom(),
                    produitEnVente.getPrix(),
                    produitEnVente.getDescription(),
//                    produitEnVente.getMarque(),
                    5));
        }

        return produitEnVenteOut;
    }

    @RequestMapping("/search/{id}")
    public String search(@PathVariable long id){
        String customer = "";
        customer = produitEnVenteRepository.findById(id).toString();
        return customer;
    }
}