package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.BadRequestException;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.repository.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Data
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer create(Customer customer) throws IllegalStateException {
//        TODO Avoir des meilleurs criteres de recherche d unicite
        if (customerRepository.findByFirstnameAndLastname(customer.getFirstname(), customer.getLastname()).isPresent()) {
            throw new BadRequestException("customer " + customer.getFirstname() + " - " + customer.getLastname() + " does already exist");
        }

        return customerRepository.save(customer);
    }

    public Customer update(UUID id, Customer newCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("customer " + id + " does not exist"));
        customer.setFirstname(newCustomer.getFirstname());
        customer.setLastname(newCustomer.getLastname());
        customer.setGender(newCustomer.getGender());
        return customerRepository.save(customer);
    }

    public void delete(UUID id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("product " + id + " don't exist");
        }
        customerRepository.deleteById(id);
    }

    public Optional<Customer> findOne(UUID id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }
}
