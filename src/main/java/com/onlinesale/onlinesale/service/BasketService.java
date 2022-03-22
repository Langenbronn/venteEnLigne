package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Basket;
import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.model.data.Ordered;
import com.onlinesale.onlinesale.repository.BasketRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Data
@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    CustomerService customerService;

    public Basket create(Basket basket) throws IllegalStateException {
        Customer customer = customerService.findById(basket.getCustomer().getId())
                .orElseThrow(() -> new NotFoundRequestException("customer " + basket.getCustomer().getId() + " does not exist"));

        basket.setCustomer(customer);
        return basketRepository.save(basket);
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
