package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.*;
import com.onlinesale.onlinesale.repository.BasketItemRepository;
import com.onlinesale.onlinesale.repository.BasketRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public BasketItem create(BasketItem basketItem) throws IllegalStateException {
        Stock stock = stockService.findById(basketItem.getStock().getId())
                .orElseThrow(() -> new NotFoundRequestException("stock " + basketItem.getStock().getId() + " does not exist"));
        basketItem.setStock(stock);

        Basket basket = basketRepository.findById(basketItem.getBasket().getId())
                .orElseThrow(() -> new NotFoundRequestException("basket " + basketItem.getBasket().getId() + " does not exist"));
        basketItem.setBasket(basket);

        return basketItemRepository.save(basketItem);
    }

    //    TODO change
    public BasketItem update(UUID id, BasketItem newBasketItem) {
        BasketItem basketItem = basketItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("ordered item " + id + " does not exist"));

        basketItem.setQuantity(newBasketItem.getQuantity());
        basketItem.setPrice(newBasketItem.getPrice());
        return basketItemRepository.save(basketItem);
    }

    public void delete(UUID id) {
        if (basketItemRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("product " + id + " don't exist");
        }
        basketItemRepository.deleteById(id);
    }

    public Optional<BasketItem> findOne(UUID id) {
        return basketItemRepository.findById(id);
    }

    public List<BasketItem> findAll() {
        return basketItemRepository.findAll();
    }
}
