package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.*;
import com.onlinesale.onlinesale.repository.BasketItemRepository;
import com.onlinesale.onlinesale.repository.BasketRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Data
@Service
public class BasketItemService {
    @Autowired
    BasketItemRepository basketItemRepository;
    @Autowired
    StockService stockService;
    @Autowired
    BasketRepository basketRepository;

    @Transactional(rollbackFor = {Exception.class})
    public BasketItem create(BasketItem basketItem) throws IllegalStateException {
        Stock stock = stockService.findById(basketItem.getStock().getId())
                .orElseThrow(() -> new NotFoundRequestException("stock " + basketItem.getStock().getId() + " does not exist"));
        basketItem.setStock(stock);

        stockService.updateIncrement(stock.getId(), -basketItem.getQuantity());

        Basket basket = basketRepository.findById(basketItem.getBasket().getId())
                .orElseThrow(() -> new NotFoundRequestException("basket " + basketItem.getBasket().getId() + " does not exist"));
        basketItem.setBasket(basket);

        return basketItemRepository.save(basketItem);
    }

    @Transactional(rollbackFor = {Exception.class})
    public BasketItem update(UUID id, BasketItem newBasketItem) {
        BasketItem basketItem = basketItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("basket item " + id + " does not exist"));

        Stock stock = stockService.findById(basketItem.getStock().getId())
                .orElseThrow(() -> new NotFoundRequestException("stock " + basketItem.getStock().getId() + " does not exist"));
        basketItem.setStock(stock);

        if (basketItem.getQuantity() - newBasketItem.getQuantity() != 0) {
            stockService.updateIncrement(stock.getId(), basketItem.getQuantity() - newBasketItem.getQuantity());
        }

        basketItem.setQuantity(newBasketItem.getQuantity());
        basketItem.setPrice(newBasketItem.getPrice());
        return basketItemRepository.save(basketItem);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void delete(UUID id) {
        BasketItem basketItem = basketItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("basket item " + id + " does not exist"));

        stockService.updateIncrement(basketItem.getStock().getId(), basketItem.getQuantity());


        basketItemRepository.deleteById(id);
    }

    public Optional<BasketItem> findOne(UUID id) {
        return basketItemRepository.findById(id);
    }

    public List<BasketItem> findAll() {
        return basketItemRepository.findAll();
    }
}
