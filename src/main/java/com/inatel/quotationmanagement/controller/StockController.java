package com.inatel.quotationmanagement.controller;

import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.data.dto.StockByStockId;
import com.inatel.quotationmanagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/stock")
    public Stock createStockQuote(@RequestBody CreateStock stock) {
        return stockService.createQuote(stock);
    }

    @GetMapping("/stock/{stockId}")
    public List<StockByStockId> getQuotesById(@PathVariable String stockId) {
        return stockService.getQuotesByStockId(stockId);
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.findAll();
    }

    @DeleteMapping("/stockcache")
    public void deleteCache(){
        stockService.deleteStockCache();
    }

}
