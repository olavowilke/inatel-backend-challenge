package com.inatel.quotationmanagement.api;

import com.inatel.quotationmanagement.data.dto.StockFromApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class StockManagementApi {

    @Autowired
    private RestTemplate restTemplate;

    public List<StockFromApi> checkStock() {
        String url = "http://localhost:8080/stock";
        StockFromApi[] stockFromApi = restTemplate.getForEntity(url, StockFromApi[].class).getBody();

        return List.of(Objects.requireNonNull(stockFromApi));
    }

}
