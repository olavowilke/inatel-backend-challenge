package com.inatel.quotationmanagement.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HostDto {

    private String host;
    private String port;

    @Override
    public String toString() {
        return "HostDto{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }

}
