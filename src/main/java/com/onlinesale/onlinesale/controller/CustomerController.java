package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.controller.assembler.CustomerModelAssembler;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Customer;
import com.onlinesale.onlinesale.model.dto.CustomerDto;
import com.onlinesale.onlinesale.model.mapper.CustomerMapper;
import com.onlinesale.onlinesale.model.view.CustomerView;
import com.onlinesale.onlinesale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerModelAssembler customerModelAssembler;

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerView>> create(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.create(customerMapper.DtoToEntity(customerDto));
        CustomerView customerView = customerMapper.entityToView(customer);
        EntityModel<CustomerView> entityModel = customerModelAssembler.toModel(customerView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerView>> update(@PathVariable("id") UUID id, @RequestBody CustomerDto customerDto) {
        Customer customer = customerService.update(id, customerMapper.DtoToEntity(customerDto));
        CustomerView customerView = customerMapper.entityToView(customer);
        EntityModel<CustomerView> entityModel = customerModelAssembler.toModel(customerView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public EntityModel<CustomerView> findOne(@PathVariable("id") UUID id) {
        return customerService.findOne(id)
                .map(customerMapper::entityToView)
                .map(customerModelAssembler::toModel)
                .orElseThrow(() -> new NotFoundRequestException("product " + id + " does not exist"));
    }

    @GetMapping
    public CollectionModel<EntityModel<CustomerView>> findAll() {
        List<EntityModel<CustomerView>> customerViews = customerService.findAll()
                .stream()
                .map(customerMapper::entityToView)
                .map(customerModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(customerViews, linkTo(methodOn(SellerController.class).findAll()).withSelfRel());
    }

}