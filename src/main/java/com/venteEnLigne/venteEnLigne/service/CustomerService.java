package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.exception.BadRequestException;
import com.venteEnLigne.venteEnLigne.exception.NotFoundRequestException;
import com.venteEnLigne.venteEnLigne.model.data.Customer;
import com.venteEnLigne.venteEnLigne.model.dto.CustomerDto;
import com.venteEnLigne.venteEnLigne.model.mapper.CustomerMapper;
import com.venteEnLigne.venteEnLigne.model.view.CustomerView;
import com.venteEnLigne.venteEnLigne.repository.CustomerRepository;
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
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRepository;

    public CustomerView create(CustomerDto customerDto) throws IllegalStateException {
//        TODO Avoir des meilleurs criteres de recherche d unicite
        if (customerRepository.findByFirstnameAndLastname(customerDto.getFirstname(), customerDto.getLastname()).isPresent()) {
            throw new BadRequestException("customer " + customerDto.getFirstname() + " - " + customerDto.getLastname() + " does already exist");
        }

        Customer customer = customerRepository.save(new Customer(customerDto.getFirstname(),
                customerDto.getLastname(),
                customerDto.getGender()
        ));
        return customerMapper.entityToView(customer);
    }

    public CustomerView update(UUID id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("customer " + id + " does not exist"));

        customer.setId(customer.getId());
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setGender(customerDto.getGender());
        customerRepository.save(customer);
        return customerMapper.entityToView(customer);
    }

    public void delete(UUID id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("product " + id + " don't exist");
        }
        customerRepository.deleteById(id);
    }

    public Optional<CustomerView> getCustomerById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(product -> customerMapper.entityToView(product));
    }

    public List<CustomerView> findAll() {
        List<Customer> customer = customerRepository.findAll();
        return customer.stream()
                .map(e -> customerMapper.entityToView(e))
                .collect(Collectors.toList());
    }
}
