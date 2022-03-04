package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.BadRequestException;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.model.data.Seller;
import com.onlinesale.onlinesale.repository.SellerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public Seller create(Seller seller) throws IllegalStateException {

        if (sellerRepository.findByName(seller.getName()).isPresent()) {
            throw new BadRequestException("seller " + seller.getName() + " does already exist");
        }
        return sellerRepository.save(seller);
    }

    public Seller update(UUID id, Seller seller) {
        sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("seller " + id + " does not exist"));

        seller.setId(id);
        return sellerRepository.save(seller);
    }

    public void delete(UUID id) throws IllegalStateException {
        if (sellerRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("seller " + id + " don't exist");
        }
        sellerRepository.deleteById(id);
    }

    public Optional<Seller> findOne(UUID id) {
        return sellerRepository.findById(id);
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Optional<Seller> getByName(String name) {
        return sellerRepository.findByName(name);
    }

}
