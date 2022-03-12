package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Ordered;
import com.onlinesale.onlinesale.model.data.OrderedItem;
import com.onlinesale.onlinesale.model.data.Stock;
import com.onlinesale.onlinesale.repository.OrderedItemRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Data
@Service
public class OrderedItemService {
    @Autowired
    OrderedItemRepository orderedItemRepository;
    @Autowired
    StockService stockService;
    @Autowired
    OrderedService orderedService;

    public OrderedItem create(OrderedItem orderedItem) throws IllegalStateException {
        Stock stock = stockService.findById(orderedItem.getStock().getId())
                .orElseThrow(() -> new NotFoundRequestException("stock " + orderedItem.getStock().getId() + " does not exist"));
        orderedItem.setStock(stock);

        Ordered ordered = orderedService.findById(orderedItem.getOrdered().getId())
                .orElseThrow(() -> new NotFoundRequestException("ordered " + orderedItem.getOrdered().getId() + " does not exist"));
        orderedItem.setOrdered(ordered);

        return orderedItemRepository.save(orderedItem);
    }

//    TODO change
    public OrderedItem update(UUID id, OrderedItem newOrderedItem) {
        OrderedItem orderedItem =orderedItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("ordered item " + id + " does not exist"));

        orderedItem.setQuantity(newOrderedItem.getQuantity());
        orderedItem.setPrice(newOrderedItem.getPrice());
        return orderedItemRepository.save(orderedItem);
    }

    public void delete(UUID id) {
        if (orderedItemRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("product " + id + " don't exist");
        }
        orderedItemRepository.deleteById(id);
    }

    public Optional<OrderedItem> findOne(UUID id) {
        return orderedItemRepository.findById(id);
    }

    public List<OrderedItem> findAll() {
        return orderedItemRepository.findAll();
    }
}
