package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.controller.assembler.SellerModelAssembler;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Seller;
import com.onlinesale.onlinesale.model.dto.SellerDto;
import com.onlinesale.onlinesale.model.mapper.SellerMapper;
import com.onlinesale.onlinesale.model.view.SellerView;
import com.onlinesale.onlinesale.service.SellerService;
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
@RequestMapping("/api/sellers")
public class SellerController {
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    SellerService sellerService;
    @Autowired
    SellerModelAssembler sellerModelAssembler;

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@RequestBody SellerDto sellerDto) {
        Seller seller = sellerService.create(sellerMapper.dtoToEntity(sellerDto));
        SellerView sellerView = sellerMapper.entityToView(seller);
        EntityModel<SellerView> entityModel = sellerModelAssembler.toModel(sellerView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody SellerDto sellerDto) {
        Seller seller = sellerService.update(id, sellerMapper.dtoToEntity(sellerDto));
        SellerView sellerView = sellerMapper.entityToView(seller);
        EntityModel<SellerView> entityModel = sellerModelAssembler.toModel(sellerView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        sellerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public EntityModel<SellerView> findOne(@PathVariable("id") UUID id) {
        SellerView sellerView = sellerService.findOne(id)
                .map(seller -> sellerMapper.entityToView(seller))
                .orElseThrow(() -> new NotFoundRequestException("seller " + id + " does not exist"));

        return sellerModelAssembler.toModel(sellerView);
    }

    @GetMapping
    public CollectionModel<EntityModel<SellerView>> findAll() {
        List<EntityModel<SellerView>> sellerViews = sellerService.findAll()
                .stream()
                .map(seller -> sellerMapper.entityToView(seller))
                .map(sellerModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(sellerViews, linkTo(methodOn(SellerController.class).findAll()).withSelfRel());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SellerView> findByName(@PathVariable("name") String name) {
        return sellerService.findByName(name).map(seller -> new ResponseEntity<>(sellerMapper.entityToView(seller), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}