package com.inatel.quotationmanagement.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Stock {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private UUID id;

    private String stockId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "stock",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
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
