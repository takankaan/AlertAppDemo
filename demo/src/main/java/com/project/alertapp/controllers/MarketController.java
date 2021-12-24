package com.project.alertapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.alertapp.response.StockMarketView;
import com.project.alertapp.response.StockValues;
import com.project.alertapp.services.StockValueService;
/**
 * This class provides REST request for stock values.
 * @author KaanSarigul
 *
 */
@RestController
@RequestMapping("/market")
public class MarketController {
	@Autowired
	StockValueService stockValueService;
	
	/**
	 * This method sends request to stockValueService to get all saved stocks.
	 * @return Returns all saved stocks.
	 */
	@GetMapping
	public List<StockMarketView> getAllStocks(){
		return stockValueService.getAllStocks();
	}
	/**
	 * This method sends request to stockValuesService to get one stock.
	 * @param ticker Ticker contains stockSymbol, it cannot be null.
	 * @param dataMode Data mode specifies historical data, it can be null.
	 * @return Returns stocks detail values with StockValues object.
	 */
	@GetMapping("/{ticker}")
	public StockValues getOneStock(@PathVariable String ticker,@RequestParam Optional<String> dataMode) {
		return stockValueService.getOneStock(ticker,dataMode);
	}
}
