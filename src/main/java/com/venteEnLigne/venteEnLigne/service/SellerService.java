package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
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
        sellerRepository.saveAll(Arrays.asList(new SellerEntity("Space Cowboys")
                , new SellerEntity("Domino")
                , new SellerEntity("Saturn")
                , new SellerEntity("Ikea")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public SellerView create(@RequestBody SellerDto sellerDto) throws IllegalStateException {

        if (sellerRepository.findByName(sellerDto.getName()).isPresent()) {
            throw new IllegalStateException("seller " + sellerDto.getName() + " does already exist");
        }
        SellerEntity sellerData = sellerRepository.save(new SellerEntity(sellerDto.getName()));
        return sellerMapper.entityToView(sellerData);
    }

    public SellerView update(long id, SellerDto sellerDto) {
        Optional<SellerEntity> sellerData = sellerRepository.findById(id);

        if (sellerData.isPresent()) {
            SellerEntity _sellerEntity = sellerData.get();
            _sellerEntity.setId(_sellerEntity.getId());
            _sellerEntity.setName(sellerDto.getName());
            sellerRepository.save(_sellerEntity);
            return sellerMapper.entityToView(_sellerEntity);
        } else {
            throw new IllegalStateException("seller " + id + " don't exist");
        }
    }

    public void delete(long id) throws IllegalStateException {
        if (sellerRepository.findById(id).isEmpty()) {
            throw new IllegalStateException("seller " + id + " don't exist");
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
