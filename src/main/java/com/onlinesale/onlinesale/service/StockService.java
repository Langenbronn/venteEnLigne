package com.onlinesale.onlinesale.service;

import com.onlinesale.onlinesale.exception.BadRequestException;
import com.onlinesale.onlinesale.exception.NotFoundRequestException;
import com.onlinesale.onlinesale.repository.ProductRepository;
import com.onlinesale.onlinesale.repository.SellerRepository;
import com.onlinesale.onlinesale.repository.StockRepository;
import com.onlinesale.onlinesale.model.data.Product;
import com.onlinesale.onlinesale.model.data.Seller;
import com.onlinesale.onlinesale.model.data.Stock;
import com.onlinesale.onlinesale.model.dto.StockDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductService productService;
    @Autowired
    SellerService sellerService;

    public Stock create(Stock stock) throws IllegalStateException {
        Seller seller = sellerService.findById(stock.getSeller().getId())
                .orElseThrow(() -> new NotFoundRequestException("seller " + stock.getSeller().getId() + " does not exist"));
        stock.setSeller(seller);

        Product product = productService.findById(stock.getProduct().getId())
                .orElseThrow(() -> new NotFoundRequestException("product " + stock.getProduct().getId() + " does not exist"));
        stock.setProduct(product);

        if (stockRepository.findBySellerIdAndProductId(stock.getSeller().getId(), stock.getProduct().getId()).isPresent()) {
            throw new BadRequestException("stock: seller " + seller.getId() + " already have a stock of product " + product.getId());
        }

        return stockRepository.save(stock);
    }

    public Stock update(UUID id, Stock stock) {
        stockRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("stock " + id + " does not exist"));

        stock.setId(id);
        return stockRepository.save(stock);
    }

    public void delete(UUID id) throws IllegalStateException {
        if (stockRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("stock " + id + " don't exist");
        }
        stockRepository.deleteById(id);
    }

    public Optional<Stock> findOne(UUID id) {
        return stockRepository.findById(id);
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }
}
