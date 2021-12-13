package com.deneme.demo.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.demo.entities.Alerts;

public interface AlertsRepository extends JpaRepository<Alerts, Long> {

	List<Alerts> findAllByUserIdAndDeletedFalse(Long userId);

	
	List<Alerts> findAllByStockSymbolAndDeletedFalse(String stockSymbol);
	
	Alerts findByUserIdAndId(Long userId,Long id);
	
	List<Alerts> findByUserIdAndStockSymbolAndDeletedFalse(Long userId, Optional<String> stockSymbol);

}
