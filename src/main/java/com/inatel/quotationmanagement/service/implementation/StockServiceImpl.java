package com.inatel.quotationmanagement.service.implementation;

import com.inatel.quotationmanagement.api.StockManagementApiService;
import com.inatel.quotationmanagement.api.dto.StockFromApi;
import com.inatel.quotationmanagement.data.Quote;
import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.data.dto.StockByStockId;
import com.inatel.quotationmanagement.repository.QuoteRepository;
import com.inatel.quotationmanagement.repository.StockRepository;
import com.inatel.quotationmanagement.service.StockService;
import com.inatel.quotationmanagement.service.validation.StockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private StockManagementApiService stockManagementApiService;

    @Override
    public Stock createQuote(CreateStock stock) {
        String stockId = stock.getStockId();
        Map<LocalDate, String> quoteMap = stock.getQuotes();

        List<Quote> quotes = quoteMap.entrySet().stream()
                .map(localDateStringEntry -> {
                    LocalDate date = localDateStringEntry.getKey();
                    String price = localDateStringEntry.getValue();
                    return new Quote(date, price);
                })
                .collect(Collectors.toList());

        List<StockFromApi> stocksFromApi = stockManagementApiService.getAllStocks();
        StockValidator.validateStockExists(stocksFromApi, stockId);

        return stockRepository.save(new Stock(stockId, quotes));
    }

    @Override
    public List<StockByStockId> getQuotesByStockId(String stockId) {
        List<Stock> stocks = stockRepository.findByStockId(stockId);

        return stocks.stream()
                .map(StockByStockId::new)
                .collect(Collectors.toList());
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
