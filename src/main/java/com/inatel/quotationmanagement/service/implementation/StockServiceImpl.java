package com.inatel.quotationmanagement.service.implementation;

import com.inatel.quotationmanagement.data.Quote;
import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.data.dto.StockByStockId;
import com.inatel.quotationmanagement.repository.QuoteRepository;
import com.inatel.quotationmanagement.repository.StockRepository;
import com.inatel.quotationmanagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public Stock createStock(CreateStock stock) {

        String stockId = stock.getStockId();
        Map<LocalDate, String> quoteMap = stock.getQuotes();

        List<Quote> quotes = quoteMap.entrySet().stream()
                .map(localDateStringEntry -> {
                    LocalDate date = localDateStringEntry.getKey();
                    String price = localDateStringEntry.getValue();
                    return new Quote(date, price);
                })
                .collect(Collectors.toList());

        quoteRepository.saveAll(quotes);

        return stockRepository.save(new Stock(stockId, quotes));
    }

    @Override
    public StockByStockId getQuotesByStockId(String stockId) {
        List<Stock> stocks = stockRepository.findByStockId(stockId);

//        Map<String, List<Stock>> collect = stocks.stream()
//                .collect(Collectors.groupingBy(Stock::getStockId));

        List<List<Quote>> collect1 = stocks.stream()
                .map(stock -> stock.getQuotes())
                .collect(Collectors.toList());

        List<String> collect2 = collect1.stream()
                .flatMap(quotes -> {
                    return quotes.stream()
                            .map(quote -> quote.getPrice());
                }).collect(Collectors.toList());


        ;
        /*

            "<stockId, List<Stock>"

            >list<Stock>
            >>list<Quote>


            <LocalDate date, String price>
         */

        //list<stock>

        //list<quotes>
        //date, price


        //Map<date, price>

        return null;
    }

}
