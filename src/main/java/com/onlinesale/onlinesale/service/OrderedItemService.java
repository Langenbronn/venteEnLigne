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

    public OrderedItem create(OrderedItem orderedItemDto) throws IllegalStateException {
        Stock stock = stockService.findById(orderedItemDto.getStock().getId())
                .orElseThrow(() -> new NotFoundRequestException("stock " + orderedItemDto.getStock().getId() + " does not exist"));
        orderedItemDto.setStock(stock);

        Ordered ordered = orderedService.findById(orderedItemDto.getOrdered().getId())
                .orElseThrow(() -> new NotFoundRequestException("ordered " + orderedItemDto.getOrdered().getId() + " does not exist"));
        orderedItemDto.setOrdered(ordered);

        return orderedItemRepository.save(orderedItemDto);
    }

    public OrderedItem update(UUID id, OrderedItem orderedItem) {
        orderedItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("ordered item " + id + " does not exist"));

        orderedItem.setId(id);
        orderedItem.setQuantity(orderedItem.getQuantity());
        orderedItem.setPrice(orderedItem.getPrice());
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
