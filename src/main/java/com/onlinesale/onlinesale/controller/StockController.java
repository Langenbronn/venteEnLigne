package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.data.Stock;
import com.onlinesale.onlinesale.model.dto.StockDto;
import com.onlinesale.onlinesale.model.mapper.StockMapper;
import com.onlinesale.onlinesale.model.view.StockView;
import com.onlinesale.onlinesale.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    StockMapper stockMapper;
    @Autowired
    StockService stockService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody StockDto stockDto) {
        Stock stock = stockService.create(stockMapper.dtoToEntity(stockDto));
        return new ResponseEntity<>(stock.getId() + " has been created", HttpStatus.CREATED);
    }

    //    TODO stockDto avec seulement quantite
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody StockDto stockDto) {
        Stock stock = stockService.update(id, stockMapper.dtoToEntity(stockDto));
        return new ResponseEntity<>(stock.getId() + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        stockService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockView> findOne(@PathVariable("id") UUID id) {
        return stockService.findOne(id).map(stock -> new ResponseEntity<>(stockMapper.entityToView(stock), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<StockView>> findAll() {
        List<StockView> stockViews = stockService.findAll()
                .stream().map(stock -> stockMapper.entityToView(stock))
                .collect(Collectors.toList());
        return new ResponseEntity<>(stockViews, HttpStatus.OK);
    }

}