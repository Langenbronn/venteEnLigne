package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.exception.BadRequestException;
import com.venteEnLigne.venteEnLigne.exception.NotFoundRequestException;
import com.venteEnLigne.venteEnLigne.model.data.Product;
import com.venteEnLigne.venteEnLigne.model.data.Seller;
import com.venteEnLigne.venteEnLigne.model.data.Stock;
import com.venteEnLigne.venteEnLigne.model.dto.StockDto;
import com.venteEnLigne.venteEnLigne.model.mapper.StockMapper;
import com.venteEnLigne.venteEnLigne.model.view.StockView;
import com.venteEnLigne.venteEnLigne.repository.ProductRepository;
import com.venteEnLigne.venteEnLigne.repository.SellerRepository;
import com.venteEnLigne.venteEnLigne.repository.StockRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
public class StockService {
    @Autowired
    StockMapper stockMapper;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;

    public StockView create(StockDto stockDto) throws IllegalStateException {
        Seller seller = sellerRepository.findById(stockDto.getIdSeller())
                .orElseThrow(() -> new NotFoundRequestException("seller " + stockDto.getIdSeller() + " does not exist"));

        Product product = productRepository.findById(stockDto.getIdProduct())
                .orElseThrow(() -> new NotFoundRequestException("product " + stockDto.getIdProduct() + " does not exist"));

        Optional<Stock> stockEntity = stockRepository.findFirstBySellerIdAndProductId(stockDto.getIdSeller(), stockDto.getIdProduct());

        if (stockEntity.isPresent()) {
            throw new BadRequestException("stock: seller " + seller.getId() + " already have a stock of product " + product.getId());
        }

        Stock stockData = stockRepository.save(new Stock(stockDto.getQuantity(), product, seller));
        return stockMapper.entityToView(stockData);
    }

    public StockView update(UUID id, StockDto stockDto) {
        Optional<Stock> stockData = stockRepository.findById(id);

//        TODO check for update idProduct, idSeller

        if (stockData.isPresent()) {
            Stock stock = stockData.get();
            stock.setId(stock.getId());
            stock.setQuantity(stockDto.getQuantity());
            stockRepository.save(stock);
            return stockMapper.entityToView(stock);
        } else {
            throw new NotFoundRequestException("stock " + id + " don't exist");
        }
    }

    public void delete(UUID id) throws IllegalStateException {
        if (stockRepository.findById(id).isEmpty()) {
            throw new NotFoundRequestException("stock " + id + " don't exist");
        }
        stockRepository.deleteById(id);
    }

    public Optional<StockView> getStockById(UUID id) {
        Optional<Stock> stockData = stockRepository.findById(id);
        return stockData.map(stock -> stockMapper.entityToView(stock));
    }

    public List<StockView> findAll() {
        List<Stock> stockEntities = stockRepository.findAll();
        return stockEntities.stream()
                .map(e -> stockMapper.entityToView(e))
                .collect(Collectors.toList());
    }
}
