package com.inatel.quotationmanagement.api;

import com.inatel.quotationmanagement.api.dto.StockFromApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Component
public class StockManagementApiService {

    private static final String BASE_URL = "http://localhost:8080";

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "stock-cache")
    public List<StockFromApi> getAllStocks() {
        String url = BASE_URL + "/stock";
        StockFromApi[] stockFromApi = restTemplate.getForEntity(url, StockFromApi[].class).getBody();

        return List.of(Objects.requireNonNull(stockFromApi));
    }

    public void registerNewStock() {
        String url = BASE_URL + "/stocks";
        String requestJson = "{\"host\": \"localhost\",\"port\": \"8081\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        restTemplate.postForObject(url, entity, String.class);
    }

    @CacheEvict(value = "stock-cache")
    public void deleteStockCache() {}

    @PostConstruct
    public void register() {
        String url = BASE_URL + "/notification";
        String requestJson = "{\"host\": \"localhost\",\"port\": \"8081\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        restTemplate.postForObject(url, entity, String.class);
    }

}
