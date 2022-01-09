package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.exception.BadRequestException;
import com.venteEnLigne.venteEnLigne.exception.NotFoundRequestException;
import com.venteEnLigne.venteEnLigne.model.data.Seller;
import com.venteEnLigne.venteEnLigne.model.dto.SellerDto;
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
        sellerRepository.saveAll(Arrays.asList(new Seller("Space Cowboys")
                , new Seller("Domino")
                , new Seller("Saturn")
                , new Seller("Ikea")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public SellerView create(@RequestBody SellerDto sellerDto) throws IllegalStateException {

        if (sellerRepository.findByName(sellerDto.getName()).isPresent()) {
            throw new BadRequestException("seller " + sellerDto.getName() + " does already exist");
        }
        Seller sellerData = sellerRepository.save(new Seller(sellerDto.getName()));
        return sellerMapper.entityToView(sellerData);
    }

    public SellerView update(long id, SellerDto sellerDto) {
        Optional<Seller> sellerData = sellerRepository.findById(id);

        if (sellerData.isPresent()) {
            Seller _seller = sellerData.get();
            _seller.setId(_seller.getId());
            _seller.setName(sellerDto.getName());
            sellerRepository.save(_seller);
            return sellerMapper.entityToView(_seller);
        } else {
            throw new NotFoundRequestException("seller " + id + " don't exist");
        }
    }

    public void delete(long id) throws IllegalStateException {
        if (sellerRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("seller " + id + " don't exist");
        }
        sellerRepository.deleteById(id);
    }

    public Optional<SellerView> getSellerById(long id) {
        Optional<Seller> sellerData = sellerRepository.findById(id);
        return sellerData.map(seller -> sellerMapper.entityToView(seller));
    }

    public List<SellerView> finddAll() {
        List<Seller> sellerData = sellerRepository.findAll();
        return sellerData.stream()
                .map(e -> sellerMapper.entityToView(e))
                .collect(Collectors.toList());
    }

    public Optional<SellerView> getSellerByName(String name) {
        Optional<Seller> sellerData = sellerRepository.findByName(name);
        return sellerData.map(seller -> sellerMapper.entityToView(seller));
    }

}
