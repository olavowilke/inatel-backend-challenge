package com.inatel.quotationmanagement.api;

import com.google.gson.Gson;
import com.inatel.quotationmanagement.api.dto.HostDto;
import com.inatel.quotationmanagement.api.dto.StockFromApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${stock-mgmt-url}")
    private String stockMgmtUrl;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "stock-cache")
    public List<StockFromApi> getAllStocks() {
        String url = stockMgmtUrl + "/stock";
        StockFromApi[] stockFromApi = restTemplate.getForEntity(url, StockFromApi[].class).getBody();

        return List.of(Objects.requireNonNull(stockFromApi));
    }

    @CacheEvict(value = "stock-cache")
    public void deleteStockCache() {
    }

    @PostConstruct
    public void register() {
        String url = stockMgmtUrl + "/notification";
        String requestJson = new Gson().toJson(new HostDto("localhost", serverPort));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        restTemplate.postForObject(url, entity, String.class);
    }

}
