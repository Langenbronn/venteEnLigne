package com.venteEnLigne.venteEnLigne.controller;

import com.venteEnLigne.venteEnLigne.model.dto.CustomerDto;
import com.venteEnLigne.venteEnLigne.model.view.CustomerView;
import com.venteEnLigne.venteEnLigne.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CustomerDto customerDto) {
        CustomerView customerView = customerService.create(customerDto);
        return new ResponseEntity<>(customerView.getFirstname() + " - " + customerView.getLastname() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody CustomerDto customerDto) {
        CustomerView customerView = customerService.update(id, customerDto);
        return new ResponseEntity<>(customerView.getFirstname() + " - " + customerView.getLastname() + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        customerService.delete(id);
        return new ResponseEntity<>(id + " has been deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CustomerView> getCustomerById(@PathVariable("id") long id) {
        return customerService.getCustomerById(id).map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CustomerView>> finddAll() {
        return new ResponseEntity<>(customerService.finddAll(), HttpStatus.OK);
    }

}