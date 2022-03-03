package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.model.dto.CustomerDto;
import com.onlinesale.onlinesale.model.view.CustomerView;
import com.onlinesale.onlinesale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@RequestBody CustomerDto customerDto) {
        CustomerView customerView = customerService.create(customerDto);
        return new ResponseEntity<>(customerView.getFirstname() + " - " + customerView.getLastname() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody CustomerDto customerDto) {
        CustomerView customerView = customerService.update(id, customerDto);
        return new ResponseEntity<>(customerView.getFirstname() + " - " + customerView.getLastname() + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        customerService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerView> findOne(@PathVariable("id") UUID id) {
        return customerService.findOne(id).map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<CustomerView>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

}