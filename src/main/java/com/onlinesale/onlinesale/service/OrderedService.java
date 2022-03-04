package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.model.data.Ordered;
import com.onlinesale.onlinesale.repository.OrderedRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Data
@Service
public class OrderedService {
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    CustomerService customerService;

    public Ordered create(Ordered ordered) throws IllegalStateException {
        Customer customer = customerService.findById(ordered.getCustomer().getId())
                .orElseThrow(() -> new NotFoundRequestException("customer " + ordered.getCustomer().getId() + " does not exist"));

        ordered.setCustomer(customer);


//        List<OrderedItem> orderedItems = new ArrayList<>();
//        for (UUID idOrderedItem : orderedDto.getIdOrderedItems()) {
//            OrderedItem orderedItem = orderedItemRepository.findById(idOrderedItem)
//                    .orElseThrow(() -> new NotFoundRequestException("orderedItem " + idOrderedItem + " does not exist"));
//
//            orderedItems.add(orderedItem);
//        }
        return orderedRepository.save(ordered);
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

    public Optional<Ordered> findOne(UUID id) {
        return orderedRepository.findById(id);
    }

    public List<Ordered> findAll() {
        return orderedRepository.findAll();
    }

    public Optional<Ordered> findById(UUID id) {
        return orderedRepository.findById(id);
    }
}
