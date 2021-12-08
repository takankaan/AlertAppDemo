package com.deneme.demo.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.demo.entities.Alerts;

public interface AlertsRepository extends JpaRepository<Alerts, Long> {

	List<Alerts> findAllByUserId(Long userId);

	List<Alerts> findAllByStockId(Long stockId);
	
	Alerts findByUserIdAndId(Long userId,Long id);
	
	List<Alerts> findByUserIdAndStockId(Long userId, Optional<Long> stockId);

}
