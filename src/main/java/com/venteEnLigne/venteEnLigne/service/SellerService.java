package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
import com.venteEnLigne.venteEnLigne.model.mapper.SellerMapper;
import com.venteEnLigne.venteEnLigne.model.view.SellerView;
import com.venteEnLigne.venteEnLigne.repository.SellerRepository;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class SellerService {
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    SellerRepository sellerRepository;

    public ResponseEntity<HttpStatus> initData() {
        sellerRepository.saveAll(Arrays.asList(new SellerEntity("Philibert")
                , new SellerEntity("Domino")
                , new SellerEntity("Saturn")
                , new SellerEntity("Ikea")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public SellerView create(@RequestBody SellerEntity sellerEntity) throws IllegalStateException {

        if (sellerRepository.findByName(sellerEntity.getName()).isPresent()) {
            throw new IllegalStateException(sellerEntity.getName() + " does already exist");
        }
        SellerEntity sellerData = sellerRepository.save(new SellerEntity(sellerEntity.getName()));
        return sellerMapper.entityToView(sellerData);
    }

    public ResponseEntity<SellerEntity> update(long id, SellerEntity sellerEntity) {
        Optional<SellerEntity> sellerData = sellerRepository.findById(id);

        if (sellerData.isPresent()) {
            SellerEntity _sellerEntity = sellerData.get();
            _sellerEntity.setId(sellerData.get().getId());
            _sellerEntity.setName(sellerEntity.getName());
            return new ResponseEntity<>(sellerRepository.save(_sellerEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void delete(long id) throws IllegalStateException {
        if (sellerRepository.findById(id).isEmpty()) {
            throw new IllegalStateException(id + " don't exist");
        }
        sellerRepository.deleteById(id);
    }

    public Optional<SellerView> getSellerById(long id) {
        Optional<SellerEntity> sellerData = sellerRepository.findById(id);
        return sellerData.map(seller -> sellerMapper.entityToView(seller));
    }

    public List<SellerView> finddAll() {
        List<SellerEntity> sellerEntityData = sellerRepository.findAll();
        return sellerEntityData.stream()
                .map(e -> sellerMapper.entityToView(e))
                .collect(Collectors.toList());
    }

    public Optional<SellerView> getSellerByName(String name) {
        Optional<SellerEntity> sellerData = sellerRepository.findByName(name);
        return sellerData.map(seller -> sellerMapper.entityToView(seller));
    }

}
