package com.inatel.quotationmanagement.service.implementation;

import com.inatel.quotationmanagement.api.StockManagementApiService;
import com.inatel.quotationmanagement.api.dto.StockFromApi;
import com.inatel.quotationmanagement.data.Quote;
import com.inatel.quotationmanagement.data.Stock;
import com.inatel.quotationmanagement.data.dto.CreateStock;
import com.inatel.quotationmanagement.exception_handler.exception.StockNotFoundException;
import com.inatel.quotationmanagement.repository.StockRepository;
import com.inatel.quotationmanagement.service.validation.StockValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockManagementApiService stockManagementApiServiceMock;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockValidator stockValidator;


    private CreateStock createStock;
    private Stock stock;
    private StockFromApi stockFromApi;
    private List<StockFromApi> stocksFromApi;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);

        String stockId = "petr1";
        Map<LocalDate, String> quotesMap = Map.of(
                LocalDate.now(), "10",
                LocalDate.now().minusDays(1), "20",
                LocalDate.now().minusDays(4), "30");

        this.createStock = new CreateStock(stockId, quotesMap);

        Quote quote1 = new Quote(LocalDate.now().minusDays(1), "10");
        Quote quote2 = new Quote(LocalDate.now().minusDays(2), "20");
        Quote quote3 = new Quote(LocalDate.now().minusDays(3), "30");
        this.stock = new Stock("petr4", List.of(quote1, quote2, quote3));

        StockFromApi stockFromApi = new StockFromApi("petr4", "test");
        StockFromApi stockFromApi1 = new StockFromApi("petr5", "test");
        this.stocksFromApi = List.of(stockFromApi, stockFromApi1);
    }

    @AfterEach
    public void wipe() {
        stockRepository.deleteAll();
    }

    @Test
    public void createStock_Success() {
        when(stockManagementApiServiceMock.getAllStocks()).thenReturn(List.of());
        doNothing().when(stockValidator).validateStockFromAPi(anyList(), anyString());
        when(stockRepository.findByStockId(anyString())).thenReturn(new Stock());
        when(stockValidator.validateStock(any(CreateStock.class), any(Stock.class))).thenReturn(new Stock());

        stockService.createStock(createStock);

        verify(stockManagementApiServiceMock, times(1)).getAllStocks();
        verify(stockValidator, times(1)).validateStockFromAPi(anyList(), anyString());
        verify(stockRepository, times(1)).findByStockId(anyString());
        verify(stockValidator, times(1)).validateStock(any(CreateStock.class), any(Stock.class));
    }

    @Test
    public void createStock_StockDoesNotExist() {
        assertThrows(StockNotFoundException.class, () -> {
            when(stockManagementApiServiceMock.getAllStocks()).thenReturn(stocksFromApi);
            doCallRealMethod().when(stockValidator).validateStockFromAPi(anyList(), anyString());
            stockService.createStock(createStock);
        });
    }

    @Test
    public void getQuotesByStockId_Success() {
        when(stockRepository.findByStockId(anyString())).thenReturn(stock);

        stockService.getQuotesByStockId(stock.getStockId());

        verify(stockRepository, times(1)).findByStockId(anyString());
    }

    @Test
    public void findAll_Success(){
        when(stockRepository.findAll()).thenReturn(List.of());

        stockService.findAll();

        verify(stockRepository, times(1)).findAll();
    }

    @Test
    public void deleteStockCache(){
        doNothing().when(stockManagementApiServiceMock).deleteStockCache();

        stockService.deleteStockCache();

        verify(stockManagementApiServiceMock, times(1)).deleteStockCache();
    }




}
