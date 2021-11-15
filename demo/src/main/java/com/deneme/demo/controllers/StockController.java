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

import com.deneme.demo.entities.Stock;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.services.StockService;



@RestController
@RequestMapping("/market")
public class StockController {
	
	private StockService stockService;
	
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@GetMapping
	public List<Stock> getAllStock(){
		return stockService.getAllStock();
	}
	
	@GetMapping("/{stockId}")
	public Stock getOneStock(@PathVariable Long stockId) {
		return stockService.getOneStock(stockId);
	}
	
	@PostMapping
	public Stock saveOneStock(@RequestBody Stock newStock) {
		return stockService.saveOneStock(newStock);
	}
	
	@DeleteMapping("/{stockId}")
	public void deleteOneStock(@PathVariable Long stockId) {
		stockService.deleteStock(stockId);
	}
	@PutMapping("/{stockId}")
	public Stock softDeleteStock(@PathVariable Long stockId,@RequestBody SoftDeleteRequest deleteRequest) {
		return stockService.softDeleteStock(stockId, deleteRequest);
	}
}
