package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Basket;
import com.onlinesale.onlinesale.model.data.BasketItem;
import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.repository.BasketItemRepository;
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
    BasketItemRepository basketItemRepository;
    @Autowired
    CustomerService customerService;

    public Basket create(Basket newBasket) throws IllegalStateException {


        Customer customer = customerService.findById(newBasket.getCustomer().getId())
                .orElseThrow(() -> new NotFoundRequestException("customer " + newBasket.getCustomer().getId() + " does not exist"));

        //        TODO check une basket par customer

        Set<BasketItem> basketItems = new HashSet<>();

        for (BasketItem newBasketItem : newBasket.getBasketItems()) {
            BasketItem basketItem = basketItemRepository.findById(newBasketItem.getId())
                    .orElseThrow(() -> new NotFoundRequestException("customer " + newBasket.getCustomer().getId() + " does not exist"));
            basketItems.add(basketItem);
        }

        newBasket.setCustomer(customer);
        newBasket.setBasketItems(basketItems);
        return basketRepository.save(newBasket);
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
