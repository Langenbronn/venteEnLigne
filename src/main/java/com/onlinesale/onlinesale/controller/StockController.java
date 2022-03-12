package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.controller.assembler.StockModelAssembler;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Stock;
import com.onlinesale.onlinesale.model.dto.StockDto;
import com.onlinesale.onlinesale.model.mapper.StockMapper;
import com.onlinesale.onlinesale.model.view.StockView;
import com.onlinesale.onlinesale.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    StockMapper stockMapper;
    @Autowired
    StockService stockService;
    @Autowired
    StockModelAssembler stockModelAssembler;

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<StockView>> create(@RequestBody StockDto stockDto) {
        Stock stock = stockService.create(stockMapper.dtoToEntity(stockDto));
        StockView stockView = stockMapper.entityToView(stock);
        EntityModel<StockView> entityModel = stockModelAssembler.toModel(stockView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //    TODO stockDto avec seulement quantite
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StockView>> update(@PathVariable("id") UUID id, @RequestBody StockDto stockDto) {
        Stock stock = stockService.update(id, stockMapper.dtoToEntity(stockDto));
        StockView stockView = stockMapper.entityToView(stock);
        EntityModel<StockView> entityModel = stockModelAssembler.toModel(stockView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        stockService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public EntityModel<StockView> findOne(@PathVariable("id") UUID id) {
        return stockService.findOne(id)
                .map(stockMapper::entityToView)
                .map(stockModelAssembler::toModel)
                .orElseThrow(() -> new NotFoundRequestException("stock " + id + " does not exist"));
    }

    @GetMapping
    public CollectionModel<EntityModel<StockView>> findAll() {
        List<EntityModel<StockView>> stockViews = stockService.findAll()
                .stream()
                .map(stockMapper::entityToView)
                .map(stockModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(stockViews, linkTo(methodOn(SellerController.class).findAll()).withSelfRel());
    }

}