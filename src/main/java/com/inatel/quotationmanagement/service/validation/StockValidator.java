package com.inatel.quotationmanagement.service.validation;

import com.inatel.quotationmanagement.api.dto.StockFromApi;
import com.inatel.quotationmanagement.data.Quote;
import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.exception_handler.exception.StockNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StockValidator {

    public void validateStockFromAPi(List<StockFromApi> stocks, String stockId) {
        stocks.stream()
                .filter(stock -> stock.getId().equalsIgnoreCase(stockId))
                .findFirst()
                .orElseThrow(() -> new StockNotFoundException("Stock does not exist"));
    }

    public Stock validateStock(CreateStock createStock, Stock stockById) {
        return stockById != null ? addQuotesToStock(stockById, createStock) : newStockInstance(createStock);
    }

    private Stock addQuotesToStock(Stock stockById, CreateStock createStock) {
        List<Quote> quotes = getQuotes(createStock);
        quotes.forEach(quote -> stockById.getQuotes().add(quote));

        return stockById;
    }

    private Stock newStockInstance(CreateStock stock) {
        String stockId = stock.getStockId();
        List<Quote> quotes = getQuotes(stock);

        return new Stock(stockId, quotes);
    }

    private List<Quote> getQuotes(CreateStock stock) {
        Map<LocalDate, String> quoteMap = stock.getQuotes();

        List<Quote> quotes = quoteMap.entrySet().stream()
                .map(localDateStringEntry -> {
                    LocalDate date = localDateStringEntry.getKey();
                    String price = localDateStringEntry.getValue();
                    return new Quote(date, price);
                })
                .collect(Collectors.toList());

        return quotes;
    }
}
