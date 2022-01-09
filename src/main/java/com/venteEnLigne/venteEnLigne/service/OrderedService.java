package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.exception.NotFoundRequestException;
import com.venteEnLigne.venteEnLigne.model.data.Customer;
import com.venteEnLigne.venteEnLigne.model.data.Ordered;
import com.venteEnLigne.venteEnLigne.model.data.OrderedItem;
import com.venteEnLigne.venteEnLigne.model.dto.OrderedDto;
import com.venteEnLigne.venteEnLigne.model.mapper.OrdererMapper;
import com.venteEnLigne.venteEnLigne.model.view.OrdererView;
import com.venteEnLigne.venteEnLigne.repository.CustomerRepository;
import com.venteEnLigne.venteEnLigne.repository.OrderedItemRepository;
import com.venteEnLigne.venteEnLigne.repository.OrderedRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public OrdererView create(@RequestBody OrderedDto orderedDto) throws IllegalStateException {
        Customer customer = customerRepository.findById(orderedDto.getIdCustomer())
                .orElseThrow(() -> new NotFoundRequestException("customer " + orderedDto.getIdCustomer() + " does not exist"));


        List<OrderedItem> orderedItems = new ArrayList<>();
        for (Long idOrderedItem : orderedDto.getIdOrderedItems()) {
            OrderedItem orderedItem = orderedItemRepository.findById(idOrderedItem)
                    .orElseThrow(() -> new NotFoundRequestException("orderedItem " + idOrderedItem + " does not exist"));

            orderedItems.add(orderedItem);
        }
        Ordered ordered = orderedRepository.save(new Ordered(customer,
                orderedItems
        ));
        return ordererMapper.entityToView(ordered);
    }

//    public OrdererView update(long id, OrderedDto orderedDto) {
//        Ordered ordered = orderedRepository.findById(id)
//                .orElseThrow(() -> new NotFoundRequestException("orderedItem " + id + " does not exist"));
//
//        ordered.setId(ordered.getId());
//        ordered.s(customerDto.getFirstname());
//        ordered.setLastname(customerDto.getLastname());
//        ordered.setGender(customerDto.getGender());
//        orderedRepository.save(ordered);
//        return ordererMapper.entityToView(ordered);
//    }

    public void delete(long id) {
        if (orderedRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("orderedItem " + id + " don't exist");
        }
        orderedRepository.deleteById(id);
    }

    public Optional<OrdererView> getCustomerById(long id) {
        Optional<Ordered> orderedData = orderedRepository.findById(id);
        return orderedData.map(ordered -> ordererMapper.entityToView(ordered));
    }

    public List<OrdererView> finddAll() {
        List<Ordered> ordereds = orderedRepository.findAll();
        return ordereds.stream()
                .map(ordered -> ordererMapper.entityToView(ordered))
                .collect(Collectors.toList());
    }
}
