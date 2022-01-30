package com.inatel.quotationmanagement.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class StockFromApi {

    private String id;
    private String description;

    @Override
    public String toString() {
        return "StockFromApi{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
