package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.*;
import com.onlinesale.onlinesale.repository.BasketRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Data
@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    BasketItemService basketItemService;
    @Autowired
    OrderedService orderedService;
    @Autowired
    OrderedItemService orderedItemService;
    @Autowired
    CustomerService customerService;

    public Basket create(Basket newBasket) throws IllegalStateException {


        Customer customer = customerService.findById(newBasket.getCustomer().getId())
                .orElseThrow(() -> new NotFoundRequestException("customer " + newBasket.getCustomer().getId() + " does not exist"));

        //        TODO check une basket par customer

        Set<BasketItem> basketItems = new HashSet<>();

        for (BasketItem newBasketItem : newBasket.getBasketItems()) {
            BasketItem basketItem = basketItemService.findOne(newBasketItem.getId())
                    .orElseThrow(() -> new NotFoundRequestException("customer " + newBasket.getCustomer().getId() + " does not exist"));
            basketItems.add(basketItem);
        }

        newBasket.setCustomer(customer);
        newBasket.setBasketItems(basketItems);
        return basketRepository.save(newBasket);
    }


    public void updateToOrderer(UUID id) {
        Basket basket = this.findOne(id)
                .orElseThrow(() -> new NotFoundRequestException("basket id " + id + " does not exist"));

        for (BasketItem basketItem : basket.getBasketItems()) {
            basketItemService.delete(basketItem.getId());
        }

        this.delete(id);


        Ordered ordered = orderedService.create(new Ordered(basket.getCustomer()));

        for (BasketItem basketItem : basket.getBasketItems()) {
            orderedItemService.create(new OrderedItem(
                    basketItem.getQuantity(),
                    basketItem.getPrice(),
                    basketItem.getStock(),
                    ordered
            ));
        }


    }

    public void delete(UUID id) {
        if (basketRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("basket " + id + " don't exist");
        }
        basketRepository.deleteById(id);
    }

    public Optional<Basket> findOne(UUID id) {
        return basketRepository.findById(id);
    }

    public List<Basket> findAll() {
        return basketRepository.findAll();
    }

    public Optional<Basket> findById(UUID id) {
        return basketRepository.findById(id);
    }
}
