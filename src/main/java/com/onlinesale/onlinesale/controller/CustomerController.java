package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.model.dto.CustomerDto;
import com.onlinesale.onlinesale.model.mapper.CustomerMapper;
import com.onlinesale.onlinesale.model.view.CustomerView;
import com.onlinesale.onlinesale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerService customerService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.create(customerMapper.DtoToEntity(customerDto));
        return new ResponseEntity<>(customer.getFirstname() + " - " + customer.getLastname() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody CustomerDto customerDto) {
        Customer customer = customerService.update(id, customerMapper.DtoToEntity(customerDto));
        return new ResponseEntity<>(customer.getFirstname() + " - " + customer.getLastname() + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        customerService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerView> findOne(@PathVariable("id") UUID id) {
        return customerService.findOne(id).map(customer -> new ResponseEntity<>(customerMapper.entityToView(customer), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<CustomerView>> findAll() {
        List<CustomerView> customerViews = customerService.findAll()
                .stream().map(customer -> customerMapper.entityToView(customer))
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerViews, HttpStatus.OK);
    }

}