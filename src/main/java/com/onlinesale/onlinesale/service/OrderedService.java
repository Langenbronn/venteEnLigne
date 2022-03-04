package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.repository.CustomerRepository;
import com.onlinesale.onlinesale.repository.OrderedItemRepository;
import com.onlinesale.onlinesale.repository.OrderedRepository;
import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.model.data.Ordered;
import com.onlinesale.onlinesale.model.dto.OrderedDto;
import com.onlinesale.onlinesale.model.mapper.OrdererMapper;
import com.onlinesale.onlinesale.model.view.OrdererView;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Data
@Service
public class OrderedService {
    @Autowired
    OrdererMapper ordererMapper;
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderedItemRepository orderedItemRepository;

    public OrdererView create(OrderedDto orderedDto) throws IllegalStateException {
        Customer customer = customerRepository.findById(orderedDto.getIdCustomer())
                .orElseThrow(() -> new NotFoundRequestException("customer " + orderedDto.getIdCustomer() + " does not exist"));


//        List<OrderedItem> orderedItems = new ArrayList<>();
//        for (UUID idOrderedItem : orderedDto.getIdOrderedItems()) {
//            OrderedItem orderedItem = orderedItemRepository.findById(idOrderedItem)
//                    .orElseThrow(() -> new NotFoundRequestException("orderedItem " + idOrderedItem + " does not exist"));
//
//            orderedItems.add(orderedItem);
//        }
        Ordered ordered = orderedRepository.save(new Ordered(customer));
        return ordererMapper.entityToView(ordered);
    }
//TODO
//    public OrdererView update(UUID id, OrderedDto orderedDto) {
//        Customer customer = customerRepository.findById(orderedDto.getIdCustomer())
//                .orElseThrow(() -> new NotFoundRequestException("customer " + orderedDto.getIdCustomer() + " does not exist"));
//
//        List<OrderedItem> orderedItems = new ArrayList<>();
//        for (UUID idOrderedItem : orderedDto.getIdOrderedItems()) {
//            OrderedItem orderedItem = orderedItemRepository.findById(idOrderedItem)
//                    .orElseThrow(() -> new NotFoundRequestException("orderedItem " + idOrderedItem + " does not exist"));
//            orderedItems.add(orderedItem);
//        }
//
//        Ordered ordered = orderedRepository.findById(id)
//                .orElseThrow(() -> new NotFoundRequestException("ordered " + id + " does not exist"));
//
//        ordered.setId(ordered.getId());
//        ordered.setCustomer(customer);
//        ordered.setPrice(orderedDto.getPrice());
//        orderedItemRepository.save(orderedItem);
//        return ordererItemMapper.entityToView(orderedItem);
//    }

    public void delete(UUID id) {
        if (orderedRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("orderedItem " + id + " don't exist");
        }
        orderedRepository.deleteById(id);
    }

    public Optional<OrdererView> findOne(UUID id) {
        Optional<Ordered> orderedData = orderedRepository.findById(id);
        return orderedData.map(ordered -> ordererMapper.entityToView(ordered));
    }

    public List<OrdererView> findAll() {
        List<Ordered> ordereds = orderedRepository.findAll();
        return ordereds.stream()
                .map(ordered -> ordererMapper.entityToView(ordered))
                .collect(Collectors.toList());
    }
}