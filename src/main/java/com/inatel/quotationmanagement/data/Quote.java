package com.inatel.quotationmanagement.data;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
public class Quote {

    @Id
    private UUID id;

    private LocalDate date;
    private String price;

    public Quote() {
        this.id = UUID.randomUUID();
    }

    public Quote(LocalDate date, String price) {
        this();
        this.date = date;
        this.price = price;
    }

}
