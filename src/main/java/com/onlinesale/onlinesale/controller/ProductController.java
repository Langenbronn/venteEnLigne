package com.onlinesale.onlinesale.controller;

import com.onlinesale.onlinesale.controller.assembler.ProductModelAssembler;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Product;
import com.onlinesale.onlinesale.model.dto.ProductDto;
import com.onlinesale.onlinesale.model.mapper.ProductMapper;
import com.onlinesale.onlinesale.model.view.ProductView;
import com.onlinesale.onlinesale.service.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductService productService;
    @Autowired
    ProductModelAssembler productModelAssembler;

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<ProductView>> create(@RequestBody ProductDto productDto) {
        Product product = productService.create(productMapper.dtoToEntity(productDto));
        ProductView productView = productMapper.entityToView(product);
        EntityModel<ProductView> entityModel = productModelAssembler.toModel(productView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ProductView>> update(@PathVariable("id") UUID id, @RequestBody ProductDto productDto) {
        Product product = productService.update(id, productMapper.dtoToEntity(productDto));
        ProductView productView = productMapper.entityToView(product);
        EntityModel<ProductView> entityModel = productModelAssembler.toModel(productView);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public EntityModel<ProductView> findOne(@PathVariable("id") UUID id) {
        return productService.findOne(id)
                .map(productMapper::entityToView)
                .map(productModelAssembler::toModel)
                .orElseThrow(() -> new NotFoundRequestException("product " + id + " does not exist"));
    }

    @GetMapping
    public CollectionModel<EntityModel<ProductView>> findAll() {
        List<EntityModel<ProductView>> productViews = productService.findAll()
                .stream()
                .map(productMapper::entityToView)
                .map(productModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(productViews, linkTo(methodOn(ProductController.class).findAll()).withSelfRel());
    }

}