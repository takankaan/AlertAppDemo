package com.deneme.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.demo.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
