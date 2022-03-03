package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.exception.NotFoundRequestException;
import com.venteEnLigne.venteEnLigne.model.data.Ordered;
import com.venteEnLigne.venteEnLigne.model.data.OrderedItem;
import com.venteEnLigne.venteEnLigne.model.data.Stock;
import com.venteEnLigne.venteEnLigne.model.dto.OrderedItemDto;
import com.venteEnLigne.venteEnLigne.model.mapper.OrdererItemMapper;
import com.venteEnLigne.venteEnLigne.model.view.OrdererItemView;
import com.venteEnLigne.venteEnLigne.repository.OrderedItemRepository;
import com.venteEnLigne.venteEnLigne.repository.OrderedRepository;
import com.venteEnLigne.venteEnLigne.repository.StockRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Data
@Service
public class OrderedItemService {
    @Autowired
    OrdererItemMapper ordererItemMapper;
    @Autowired
    OrderedItemRepository orderedItemRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    OrderedRepository orderedRepository;

    public OrdererItemView create(OrderedItemDto orderedItemDto) throws IllegalStateException {
        Stock stock = stockRepository.findById(orderedItemDto.getIdStock())
                .orElseThrow(() -> new NotFoundRequestException("stock " + orderedItemDto.getIdStock() + " does not exist"));

        Ordered ordered = orderedRepository.findById(orderedItemDto.getIdOrdered())
                .orElseThrow(() -> new NotFoundRequestException("ordered " + orderedItemDto.getIdStock() + " does not exist"));

        OrderedItem orderedItem = orderedItemRepository.save(new OrderedItem(orderedItemDto.getQuantity(),
                orderedItemDto.getPrice(),
                stock,
                ordered
        ));
        return ordererItemMapper.entityToView(orderedItem);
    }

    public OrdererItemView update(UUID id, OrderedItemDto orderedItemDto) {
        OrderedItem orderedItem = orderedItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("ordered item " + id + " does not exist"));

        orderedItem.setId(orderedItem.getId());
        orderedItem.setQuantity(orderedItemDto.getQuantity());
        orderedItem.setPrice(orderedItemDto.getPrice());
        orderedItemRepository.save(orderedItem);
        return ordererItemMapper.entityToView(orderedItem);
    }

    public void delete(UUID id) {
        if (orderedItemRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("product " + id + " don't exist");
        }
        orderedItemRepository.deleteById(id);
    }

    public Optional<OrdererItemView> getOrdererItemById(UUID id) {
        Optional<OrderedItem> orderedItem = orderedItemRepository.findById(id);
        return orderedItem.map(product -> ordererItemMapper.entityToView(product));
    }

    public List<OrdererItemView> findAll() {
        List<OrderedItem> orderedItem = orderedItemRepository.findAll();
        return orderedItem.stream()
                .map(e -> ordererItemMapper.entityToView(e))
                .collect(Collectors.toList());
    }
}
