package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Ordered;
import com.onlinesale.onlinesale.model.data.OrderedItem;
import com.onlinesale.onlinesale.model.data.Stock;
import com.onlinesale.onlinesale.repository.OrderedItemRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = {Exception.class})
    public OrderedItem create(OrderedItem orderedItem) throws IllegalStateException {
        Stock stock = stockService.findById(orderedItem.getStock().getId())
                .orElseThrow(() -> new NotFoundRequestException("stock " + orderedItem.getStock().getId() + " does not exist"));
        orderedItem.setStock(stock);

        stockService.updateIncrement(stock.getId(), -orderedItem.getQuantity());

        Ordered ordered = orderedService.findById(orderedItem.getOrdered().getId())
                .orElseThrow(() -> new NotFoundRequestException("ordered " + orderedItem.getOrdered().getId() + " does not exist"));
        orderedItem.setOrdered(ordered);

        return orderedItemRepository.save(orderedItem);
    }

    @Transactional(rollbackFor = {Exception.class})
    public OrderedItem update(UUID id, OrderedItem newOrderedItem) {
        OrderedItem orderedItem = orderedItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("ordered item " + id + " does not exist"));

        Stock stock = stockService.findById(orderedItem.getStock().getId())
                .orElseThrow(() -> new NotFoundRequestException("stock " + orderedItem.getStock().getId() + " does not exist"));
        orderedItem.setStock(stock);

        if (orderedItem.getQuantity() - newOrderedItem.getQuantity() != 0) {
            stockService.updateIncrement(stock.getId(), orderedItem.getQuantity() - newOrderedItem.getQuantity());
        }

        orderedItem.setQuantity(newOrderedItem.getQuantity());
        orderedItem.setPrice(newOrderedItem.getPrice());
        return orderedItemRepository.save(orderedItem);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void delete(UUID id) {
        OrderedItem orderedItem = orderedItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("ordered item " + id + " does not exist"));

        stockService.updateIncrement(orderedItem.getStock().getId(), orderedItem.getQuantity());

        orderedItemRepository.deleteById(id);
    }

    public Optional<OrderedItem> findOne(UUID id) {
        return orderedItemRepository.findById(id);
    }

    public List<OrderedItem> findAll() {
        return orderedItemRepository.findAll();
    }
}
