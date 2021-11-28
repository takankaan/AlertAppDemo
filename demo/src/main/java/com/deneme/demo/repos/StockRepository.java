package com.deneme.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.demo.entities.StockModel;

public interface StockRepository extends JpaRepository<StockModel, Long> {

	StockModel findByStockSymbol(String ticker);

	boolean existsByStockSymbol(String stockString);


}
