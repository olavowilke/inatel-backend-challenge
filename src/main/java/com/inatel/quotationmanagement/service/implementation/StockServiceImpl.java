package com.inatel.quotationmanagement.service.implementation;

import com.inatel.quotationmanagement.api.StockManagementApiService;
import com.inatel.quotationmanagement.api.dto.StockFromApi;
import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.data.dto.StockByStockId;
import com.inatel.quotationmanagement.repository.StockRepository;
import com.inatel.quotationmanagement.service.StockService;
import com.inatel.quotationmanagement.service.validation.StockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private String review = "review";

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockManagementApiService stockManagementApiService;
    @Autowired
    private StockValidator stockValidator;

    @Override
    public Stock createStock(CreateStock stock) {
        List<StockFromApi> stocksFromApi = stockManagementApiService.getAllStocks();
        stockValidator.validateStockFromAPi(stocksFromApi, stock.getStockId());

        Stock byStockId = stockRepository.findByStockId(stock.getStockId());
        Stock stock1 = stockValidator.validateStock(stock, byStockId);

        return stockRepository.save(stock1);
    }

    @Override
    public StockByStockId getQuotesByStockId(String stockId) {
        Stock byStockId = stockRepository.findByStockId(stockId);

        return new StockByStockId(byStockId);
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public void deleteStockCache() {
        stockManagementApiService.deleteStockCache();
    }

}
