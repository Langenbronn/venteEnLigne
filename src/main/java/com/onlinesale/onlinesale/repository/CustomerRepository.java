package com.onlinesale.onlinesale.repository;


import com.onlinesale.onlinesale.model.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByFirstnameAndLastname(String fistname, String lastname);
}