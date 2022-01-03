package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.exception.BadRequestException;
import com.venteEnLigne.venteEnLigne.exception.NotFoundRequestException;
import com.venteEnLigne.venteEnLigne.model.data.Customer;
import com.venteEnLigne.venteEnLigne.model.data.Product;
import com.venteEnLigne.venteEnLigne.model.dto.CustomerDto;
import com.venteEnLigne.venteEnLigne.model.dto.ProductDto;
import com.venteEnLigne.venteEnLigne.model.mapper.CustomerMapper;
import com.venteEnLigne.venteEnLigne.model.view.CustomerView;
import com.venteEnLigne.venteEnLigne.model.view.ProductView;
import com.venteEnLigne.venteEnLigne.repository.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRepository;

    public CustomerView create(@RequestBody CustomerDto customerDto) throws IllegalStateException {
        if (customerRepository.findByFirstnameAndLastname(customerDto.getFirstname(), customerDto.getLastname()).isPresent()) {
            throw new BadRequestException("customer " + customerDto.getFirstname() + " - " + customerDto.getLastname() + " does already exist");
        }

        Customer customer = customerRepository.save(new Customer(customerDto.getFirstname(),
                customerDto.getLastname(),
                customerDto.getGender()
        ));
        return customerMapper.entityToView(customer);
    }

    public CustomerView update(long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("customer " + id + " does not exist"));

        customer.setId(customer.getId());
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setGender(customerDto.getGender());
        customerRepository.save(customer);
        return customerMapper.entityToView(customer);
    }

    public void delete(long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("product " + id + " don't exist");
        }
        customerRepository.deleteById(id);
    }

    public Optional<CustomerView> getCustomerById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(product -> customerMapper.entityToView(product));
    }

    public List<CustomerView> finddAll() {
        List<Customer> customer = customerRepository.findAll();
        return customer.stream()
                .map(e -> customerMapper.entityToView(e))
                .collect(Collectors.toList());
    }
}
