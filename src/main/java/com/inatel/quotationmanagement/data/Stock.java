package com.inatel.quotationmanagement.data;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Table(name = "stock")
public class Stock {

    @Id
    private UUID uuid;

    private String stockId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "stock_quote",
            joinColumns = @JoinColumn(name = "stock_uuid"),
            inverseJoinColumns = @JoinColumn(name = "quote_uuid"))
    private Set<Quote> quotes = new HashSet<>();

    public Stock() {
        this.uuid = UUID.randomUUID();
    }

    public Stock(String stockId, List<Quote> quotes) {
        this();
        this.stockId = stockId;
        this.quotes = new HashSet<>(quotes);
    }

}
