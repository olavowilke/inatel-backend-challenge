package com.inatel.quotationmanagement.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Quote {

    @Id
    @Column(name = "quote_id")
    @EqualsAndHashCode.Include
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
