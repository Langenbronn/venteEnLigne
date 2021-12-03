package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
import com.venteEnLigne.venteEnLigne.model.mapper.SellerMapper;
import com.venteEnLigne.venteEnLigne.model.view.SellerView;
import com.venteEnLigne.venteEnLigne.repository.SellerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
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

    public ResponseEntity<HttpStatus> create(@RequestBody SellerEntity sellerEntity) {
        sellerRepository.save(new SellerEntity(sellerEntity.getName()));
        return new ResponseEntity<>(HttpStatus.OK);
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

    public ResponseEntity<HttpStatus> delete(long id) {
        try {
            sellerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

}
