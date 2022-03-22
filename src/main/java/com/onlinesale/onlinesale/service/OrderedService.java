package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.model.data.Ordered;
import com.onlinesale.onlinesale.repository.OrderedRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Data
@Service
public class OrderedService {
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    CustomerService customerService;

    public Ordered create(Ordered ordered) throws IllegalStateException {
        Customer customer = customerService.findById(ordered.getCustomer().getId())
                .orElseThrow(() -> new NotFoundRequestException("customer " + ordered.getCustomer().getId() + " does not exist"));

        ordered.setCustomer(customer);
        return orderedRepository.save(ordered);
    }

    public void delete(UUID id) {
        if (orderedRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("ordered " + id + " don't exist");
        }
        orderedRepository.deleteById(id);
    }

    public Optional<Ordered> findOne(UUID id) {
        return orderedRepository.findById(id);
    }

    public List<Ordered> findAll() {
        return orderedRepository.findAll();
    }

    public Optional<Ordered> findById(UUID id) {
        return orderedRepository.findById(id);
    }
}
