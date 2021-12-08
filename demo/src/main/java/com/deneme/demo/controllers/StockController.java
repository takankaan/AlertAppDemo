package com.deneme.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.demo.entities.StockModel;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.services.StockService;



@RestController
@RequestMapping("/stocks")
public class StockController {
	
	private StockService stockService;
	
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@GetMapping
	public List<StockModel> getAllStocks(){
		return stockService.getAllStocks();
	}
	
	@GetMapping("/{ticker}")
	public StockModel getOneStock(@PathVariable String ticker) {
		return stockService.getOneStock(ticker);
	}
	
	@PostMapping("/{ticker}")
	public StockModel saveOneStock(@PathVariable String ticker) {
		return stockService.saveOneStock(ticker);
	}
	
	@DeleteMapping("/{ticker}")
	public void deleteOneStock(@PathVariable String ticker) {
		stockService.deleteStock(ticker);
	}
	@PutMapping("/{ticker}")
	public StockModel softDeleteStock(@PathVariable String ticker,@RequestBody SoftDeleteRequest deleteRequest) {
		return stockService.softDeleteStock(ticker, deleteRequest);
	}
}
