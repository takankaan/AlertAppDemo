package com.deneme.demo.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.demo.entities.Alerts;

public interface AlertsRepository extends JpaRepository<Alerts, Long> {

	List<Alerts> findAllByUserId(Optional<Long> userId);

	List<Alerts> findByUserIdAndStockId(Optional<Long> userId, Optional<Long> stockId);

	List<Alerts> findAllByStockId(Long id);

}
