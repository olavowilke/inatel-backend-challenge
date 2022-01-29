package com.inatel.quotationmanagement.service;


import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.data.dto.StockByStockId;

public interface StockService {

    Stock createStock(CreateStock stock);

    StockByStockId getQuotesByStockId(String stockId);

}
