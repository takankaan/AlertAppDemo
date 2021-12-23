package com.project.alertapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.alertapp.entities.StockModel;
import com.project.alertapp.requests.SoftDeleteRequest;
import com.project.alertapp.services.StockService;


/**
 * This class provides REST request for stocks.
 * @author KaanSarigul
 *
 */
@RestController
@RequestMapping("/stocks")
public class StockController {
	@Autowired
	private StockService stockService;
	
	/**
	 * This method sends a request to stockService to get all stocks.
	 * @return Returns list of stocks.
	 */
	@GetMapping
	public List<StockModel> getAllStocks(){
		return stockService.getAllStocks();
	}
	/**
	 * This method sends a request to stockService to save stocks 
	 * @return Returns saved Stock
	 */
	@PostMapping
	public List<StockModel> saveStocksToDatabase(){
		return stockService.saveStocksToDatabase();
	}
	/**
	 * This method sends a request to stockService to get one stock with ticker parameter.
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 * @return Returns the specified stock data.
	 */
	@GetMapping("/{ticker}")
	public StockModel getOneStock(@PathVariable String ticker) {
		return stockService.getOneStock(ticker);
	}
	
	/**
	 * This method sends a request to stockService to save one stock with ticker parameter.
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 * @return Returns the saved stock data.
	 */
	@PostMapping("/{ticker}")
	public StockModel saveOneStock(@PathVariable String ticker) {
		return stockService.saveOneStock(ticker);
	}
	/**
	 * This method sends a request to stockService to delete one stock with ticker parameter.
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 */
	@DeleteMapping("/{ticker}")
	public void deleteOneStock(@PathVariable String ticker) {
		stockService.deleteStock(ticker);
	}
	/**
	 * This method sends a request to stockService to soft delete one stock with ticker parameter.
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 * @param deleteRequest SoftDeleteRequest object contains soft delete situation for an object.
	 * @return Returns updated stock.
	 */
	@PutMapping("/{ticker}")
	public StockModel softDeleteStock(@PathVariable String ticker,@RequestBody SoftDeleteRequest deleteRequest) {
		return stockService.softDeleteStock(ticker, deleteRequest);
	}
}
