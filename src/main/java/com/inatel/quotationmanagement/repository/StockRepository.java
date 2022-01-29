package com.inatel.quotationmanagement.repository;

import com.inatel.quotationmanagement.data.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {

    List<Stock> findByStockId(String stockId);

}
