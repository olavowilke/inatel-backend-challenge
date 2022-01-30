package com.inatel.quotationmanagement.service;


import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.data.dto.StockByStockId;

import java.util.List;

public interface StockService {

    Stock createQuote(CreateStock stock);

    StockByStockId getQuotesByStockId(String stockId);

    List<Stock> findAll();

    void deleteStockCache();

}
