package com.inatel.quotationmanagement.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@AllArgsConstructor
public class CreateStock {

    private String stockId;
    private Map<LocalDate, String> quotes;

}
