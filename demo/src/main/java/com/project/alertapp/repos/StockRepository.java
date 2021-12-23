package com.project.alertapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.alertapp.entities.StockModel;

/**
 * This repository uses for stock processes on database.
 * @author KaanSarigul
 *
 */
public interface StockRepository extends JpaRepository<StockModel, Long> {

	/**
	 * This method retrieves one stock from database with stockSymbol.
	 * @param ticker Ticker represents stockSymbol. Stock symbol is unique key belongs to a stock.
	 * @return Returns one StockModel which belongs to one specified stock. 
	 */
	StockModel findByStockSymbol(String ticker);
	
	/**
	 * This method checks if the value exists.
	 * @param ticker Ticker represents stockSymbol. Stock symbol is unique key belongs to a stock.
	 * @return If value exists returns true, else false.
	 */
	boolean existsByStockSymbol(String ticker);
	/**
	 * This method retrieves all undeleted stocks from database.
	 * @return Returns list of undeleted stocks, type of StockModel.
	 */
	List<StockModel> findByDeletedFalse();
}
