package com.inatel.quotationmanagement.service.validation;

import com.inatel.quotationmanagement.api.dto.StockFromApi;
import com.inatel.quotationmanagement.exception_handler.exception.StockNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class StockValiator {

    public static void validateStockExists(List<StockFromApi> stocks, String stockId) {
        List<StockFromApi> collect = stocks.stream()
                .filter(stock -> stock.getId().equalsIgnoreCase(stockId))
                .collect(Collectors.toList());

        if (collect.isEmpty()) {
            throw new StockNotFoundException("Stock does not exist");
        }
    }

}
