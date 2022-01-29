package com.inatel.quotationmanagement.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.UUID;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Stock {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String stockId;

    @ManyToMany
    private List<Quote> quotes;

    public Stock() {
        this.id = UUID.randomUUID();
    }

    public Stock(String stockId, List<Quote> quotes) {
        this();
        this.stockId = stockId;
        this.quotes = quotes;
    }

}
