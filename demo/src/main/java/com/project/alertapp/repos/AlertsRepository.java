package com.project.alertapp.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.alertapp.entities.Alerts;

/**
 * This repository uses for alert processes on database.
 * @author KaanSarigul
 *
 */
public interface AlertsRepository extends JpaRepository<Alerts, Long> {

	/**
	 * This method retrieves one user's undeleted alerts from database.
	 * @param userId Id of requested user.
	 * @return Returns list of alerts which belongs the user.
	 */
	List<Alerts> findAllByUserIdAndDeletedFalse(Long userId);
	
	/**
	 * This method retrieves undeleted alerts belongs to one stock from database.
	 * @param stockSymbol Stock symbol is unique key belongs to a stock. 
	 * @return Returns list of alerts which belongs to one stock.
	 */
	List<Alerts> findAllByStockSymbolAndDeletedFalse(String stockSymbol);
	/**
	 * This method retrieves the specified alert of the specified user from database. 
	 * @param userId Id of requested user.
	 * @param id Id of requested alert.
	 * @return Returns one alert which is specified.
	 */
	Alerts findByUserIdAndId(Long userId,Long id);
	
	/**
	 * This method retrieves undeleted alerts which belongs to a user with specified stock symbol from database.
	 * @param userId Id of requested user.
	 * @param stockSymbol Stock symbol is unique key belongs to a stock. 
	 * @return Returns list of alerts belongs to a user with specified stock.
	 */
	List<Alerts> findByUserIdAndStockSymbolAndDeletedFalse(Long userId, Optional<String> stockSymbol);

}
