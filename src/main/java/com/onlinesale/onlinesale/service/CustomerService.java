package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.BadRequestException;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.repository.CustomerRepository;
import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.model.dto.CustomerDto;
import com.onlinesale.onlinesale.model.mapper.CustomerMapper;
import com.onlinesale.onlinesale.model.view.CustomerView;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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

    public Customer update(UUID id, Customer customer) {
        customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("customer " + id + " does not exist"));
        customer.setId(id);
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
}
