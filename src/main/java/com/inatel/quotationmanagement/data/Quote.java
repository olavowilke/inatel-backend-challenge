package com.inatel.quotationmanagement.data;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Table(name = "quote")
public class Quote {

    @Id
    private UUID uuid;

    private LocalDate date;
    private String price;

    public Quote() {
        this.uuid = UUID.randomUUID();
    }

    public Quote(LocalDate date, String price) {
        this();
        this.date = date;
        this.price = price;
    }

}
