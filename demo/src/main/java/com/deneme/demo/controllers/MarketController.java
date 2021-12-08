package com.deneme.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.demo.response.StockMarketView;
import com.deneme.demo.response.StockValues;
import com.deneme.demo.services.StockValueService;

@RestController
@RequestMapping("/market")
public class MarketController {
	@Autowired
	StockValueService stockValueService;
	
	@GetMapping
	public List<StockMarketView> getAllStocks(){
		return stockValueService.getAllStocks();
	}
	
	@GetMapping("/{ticker}")
	public StockValues getOneStock(@PathVariable String ticker) {
		return stockValueService.getOneStock(ticker);
	}
}
