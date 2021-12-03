package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.Seller;
import com.venteEnLigne.venteEnLigne.model.mapper.SellerMapper;
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

@Data
@Service
public class SellerService {
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    SellerRepository sellerRepository;

    public ResponseEntity<HttpStatus> initData() {
        sellerRepository.saveAll(Arrays.asList(new Seller("Philibert")
                , new Seller("Domino")
                , new Seller("Saturn")
                , new Seller("Ikea")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> create(@RequestBody Seller seller) {
        sellerRepository.save(new Seller(seller.getName()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Seller> update(long id, Seller seller) {
        Optional<Seller> sellerData = sellerRepository.findById(id);

        if (sellerData.isPresent()) {
            Seller _seller = sellerData.get();
            _seller.setId(sellerData.get().getId());
            _seller.setName(seller.getName());
            return new ResponseEntity<>(sellerRepository.save(_seller), HttpStatus.OK);
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

    public ResponseEntity<Seller> getSellerById(long id) {
        Optional<Seller> sellerData = sellerRepository.findById(id);
        return sellerData.map(seller -> new ResponseEntity<>(seller, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Seller>> finddAll() {
        List<Seller> sellerData = sellerRepository.findAll();
        return new ResponseEntity<>(sellerData, HttpStatus.OK);
    }

}
