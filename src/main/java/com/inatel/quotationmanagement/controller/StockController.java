package com.inatel.quotationmanagement.controller;

import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.data.dto.StockByStockId;
import com.inatel.quotationmanagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public Stock createStockQuote(@RequestBody CreateStock stock) {
        return stockService.createStock(stock);
    }

    @GetMapping("/{stockId}")
    public StockByStockId getQuotesById(@PathVariable String stockId) {
        return stockService.getQuotesByStockId(stockId);
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        String s = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        System.out.println(s);
        return stockService.findAll();
    }

    @DeleteMapping("/stockcache")
    public void deleteCache() {
        stockService.deleteStockCache();
    }

}
