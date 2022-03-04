package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.BadRequestException;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.repository.SellerRepository;
import com.onlinesale.onlinesale.model.data.Seller;
import com.onlinesale.onlinesale.model.mapper.SellerMapper;
import com.onlinesale.onlinesale.model.view.SellerView;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
public class SellerService {
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    SellerRepository sellerRepository;

    public SellerView create(Seller seller) throws IllegalStateException {

        if (sellerRepository.findByName(seller.getName()).isPresent()) {
            throw new BadRequestException("seller " + seller.getName() + " does already exist");
        }
        Seller sellerData = sellerRepository.save(new Seller(seller.getName()));
        return sellerMapper.entityToView(sellerData);
    }

    public SellerView update(UUID id, Seller seller) {
        Optional<Seller> sellerData = sellerRepository.findById(id);

        if (sellerData.isPresent()) {
            seller.setId(id);
            sellerRepository.save(seller);
            return sellerMapper.entityToView(seller);
        } else {
            throw new NotFoundRequestException("seller " + id + " don't exist");
        }
    }

    public void delete(UUID id) throws IllegalStateException {
        if (sellerRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("seller " + id + " don't exist");
        }
        sellerRepository.deleteById(id);
    }

    public Optional<SellerView> findOne(UUID id) {
        Optional<Seller> sellerData = sellerRepository.findById(id);
        return sellerData.map(seller -> sellerMapper.entityToView(seller));
    }

    public List<SellerView> findAll() {
        List<Seller> sellerData = sellerRepository.findAll();
        return sellerData.stream()
                .map(e -> sellerMapper.entityToView(e))
                .collect(Collectors.toList());
    }

    public Optional<SellerView> getByName(String name) {
        Optional<Seller> sellerData = sellerRepository.findByName(name);
        return sellerData.map(seller -> sellerMapper.entityToView(seller));
    }

}
