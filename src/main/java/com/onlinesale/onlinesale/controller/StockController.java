package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.dto.StockDto;
import com.onlinesale.onlinesale.model.view.StockView;
import com.onlinesale.onlinesale.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody StockDto stockDto) {
        StockView stockView = stockService.create(stockDto);
        return new ResponseEntity<>(stockView.getId() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody StockDto stockDto) {
        StockView stockView = stockService.update(id, stockDto);
        return new ResponseEntity<>(stockView.getId() + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        stockService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockView> findOne(@PathVariable("id") UUID id) {
        return stockService.findOne(id).map(stock -> new ResponseEntity<>(stock, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<StockView>> findAll() {
        return new ResponseEntity<>(stockService.findAll(), HttpStatus.OK);
    }

}