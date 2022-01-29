package com.inatel.quotationmanagement.data.dto;

import com.inatel.quotationmanagement.data.Quote;
import com.inatel.quotationmanagement.data.Stock;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class StockByStockId {

    private String stockId;
    private Map<LocalDate, String> quotes;

    public StockByStockId(Stock stock){
        this.stockId = stock.getStockId();
        this.quotes = mapQuotes(stock);
    }

    private Map<LocalDate, String> mapQuotes(Stock stock) {
        return stock.getQuotes().stream()
                .collect(Collectors.toMap(Quote::getDate, Quote::getPrice));
    }

}
