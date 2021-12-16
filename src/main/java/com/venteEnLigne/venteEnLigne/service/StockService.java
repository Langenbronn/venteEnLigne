package com.venteEnLigne.venteEnLigne.service;

import com.venteEnLigne.venteEnLigne.model.data.SellerEntity;
import com.venteEnLigne.venteEnLigne.model.data.StockEntity;
import com.venteEnLigne.venteEnLigne.model.mapper.StockMapper;
import com.venteEnLigne.venteEnLigne.model.view.SellerView;
import com.venteEnLigne.venteEnLigne.model.view.StockView;
import com.venteEnLigne.venteEnLigne.repository.StockRepository;
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
public class StockService {
    @Autowired
    StockMapper stockMapper;
    @Autowired
    StockRepository stockRepository;

//    public ResponseEntity<HttpStatus> initData() {
//        stockRepository.saveAll(Arrays.asList(new SellerEntity("Philibert")
//                , new SellerEntity("Domino")
//                , new SellerEntity("Saturn")
//                , new SellerEntity("Ikea")));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    public StockView create(@RequestBody StockEntity stockEntity) throws IllegalStateException {
//TODO
//        if (stockRepository.findById(stockEntity.getId()).isPresent()) {
//            throw new IllegalStateException("seller " + stockEntity.getId() + " does already exist");
//        }
        StockEntity stockData = stockRepository.save(new StockEntity(stockEntity.getQuantity()));
        return stockMapper.entityToView(stockData);
    }

    public StockView update(long id, StockEntity stockEntity) {
        Optional<StockEntity> stockData = stockRepository.findById(id);

        if (stockData.isPresent()) {
            StockEntity _stockEntity = stockData.get();
            _stockEntity.setId(_stockEntity.getId());
            _stockEntity.setQuantity(stockEntity.getQuantity());
            stockRepository.save(_stockEntity);
            return stockMapper.entityToView(_stockEntity);
        } else {
            throw new IllegalStateException("stock " + id + " don't exist");
        }
    }

    public void delete(long id) throws IllegalStateException {
        if (stockRepository.findById(id).isEmpty()) {
            throw new IllegalStateException("stock " + id + " don't exist");
        }
        stockRepository.deleteById(id);
    }

    public Optional<StockView> getStockById(long id) {
        Optional<StockEntity> stockData = stockRepository.findById(id);
        return stockData.map(stock -> stockMapper.entityToView(stock));
    }

    public List<StockView> finddAll() {
        List<StockEntity> stockEntities = stockRepository.findAll();
        return stockEntities.stream()
                .map(e -> stockMapper.entityToView(e))
                .collect(Collectors.toList());
    }

}
