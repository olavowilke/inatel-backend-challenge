package com.inatel.quotationmanagement.service.validation;

import com.inatel.quotationmanagement.api.dto.StockFromApi;
import com.inatel.quotationmanagement.data.Quote;
import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.exception_handler.exception.StockNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockValidator {

    public static StockFromApi validateStockFromAPi(List<StockFromApi> stocks, String stockId) {
        Optional<StockFromApi> first = stocks.stream()
                .filter(stock -> stock.getId().equalsIgnoreCase(stockId))
                .findFirst();

        return first.orElseThrow(() -> new StockNotFoundException("Stock does not exist"));
    }

    public static Stock validateStock(CreateStock createStock, Stock stockById) {
        return stockById != null ? addQuotesToStock(stockById, createStock) : newStockInstance(createStock);
    }

    private static Stock addQuotesToStock(Stock stockById, CreateStock createStock) {
        List<Quote> quotes = getQuotes(createStock);
        quotes.forEach(quote -> stockById.getQuotes().add(quote));

        return stockById;
    }

    private static Stock newStockInstance(CreateStock stock) {
        String stockId = stock.getStockId();
        List<Quote> quotes = getQuotes(stock);

        return new Stock(stockId, quotes);
    }

    private static List<Quote> getQuotes(CreateStock stock) {
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
