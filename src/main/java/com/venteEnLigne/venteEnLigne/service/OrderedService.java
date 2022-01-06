package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.exception.BadRequestException;
import com.venteEnLigne.venteEnLigne.exception.NotFoundRequestException;
import com.venteEnLigne.venteEnLigne.model.data.Customer;
import com.venteEnLigne.venteEnLigne.model.dto.CustomerDto;
import com.venteEnLigne.venteEnLigne.model.dto.OrderedDto;
import com.venteEnLigne.venteEnLigne.model.mapper.OrdererMapper;
import com.venteEnLigne.venteEnLigne.model.view.CustomerView;
import com.venteEnLigne.venteEnLigne.repository.OrderedRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

//    public CustomerView create(@RequestBody OrderedDto orderedDto) throws IllegalStateException {
//        if (orderedRepository.findByFirstnameAndLastname(orderedDto.getFirstname(), orderedDto.getLastname()).isPresent()) {
//            throw new BadRequestException("customer " + orderedDto.getFirstname() + " - " + orderedDto.getLastname() + " does already exist");
//        }
//
//        Customer customer = orderedRepository.save(new Customer(orderedDto.getFirstname(),
//                orderedDto.getLastname(),
//                orderedDto.getGender()
//        ));
//        return ordererMapper.entityToView(customer);
//    }
//
//    public CustomerView update(long id, CustomerDto customerDto) {
//        Customer customer = orderedRepository.findById(id)
//                .orElseThrow(() -> new NotFoundRequestException("customer " + id + " does not exist"));
//
//        customer.setId(customer.getId());
//        customer.setFirstname(customerDto.getFirstname());
//        customer.setLastname(customerDto.getLastname());
//        customer.setGender(customerDto.getGender());
//        orderedRepository.save(customer);
//        return ordererMapper.entityToView(customer);
//    }
//
//    public void delete(long id) {
//        if (orderedRepository.findById(id).isEmpty()) {
//            throw new NotFoundRequestException("product " + id + " don't exist");
//        }
//        orderedRepository.deleteById(id);
//    }
//
//    public Optional<CustomerView> getCustomerById(long id) {
//        Optional<Customer> customer = orderedRepository.findById(id);
//        return customer.map(product -> ordererMapper.entityToView(product));
//    }
//
//    public List<CustomerView> finddAll() {
//        List<Customer> customer = orderedRepository.findAll();
//        return customer.stream()
//                .map(e -> ordererMapper.entityToView(e))
//                .collect(Collectors.toList());
//    }
}
