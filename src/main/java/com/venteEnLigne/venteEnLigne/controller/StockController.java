package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.dto.StockDto;
import com.venteEnLigne.venteEnLigne.model.view.StockView;
import com.venteEnLigne.venteEnLigne.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody StockDto stockDto) {
        StockView stockView = stockService.create(stockDto);
        return new ResponseEntity<>(stockView.getId() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody StockDto stockDto) {
        StockView stockView = stockService.update(id, stockDto);
        return new ResponseEntity<>(stockView.getId() + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        stockService.delete(id);
        return new ResponseEntity<>(id + " has been deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<StockView> getProduitById(@PathVariable("id") long id) {
        return stockService.getStockById(id).map(stock -> new ResponseEntity<>(stock, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<StockView>> finddAll() {
        return new ResponseEntity<>(stockService.finddAll(), HttpStatus.OK);
    }

}