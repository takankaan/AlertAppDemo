package com.deneme.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deneme.demo.entities.Stock;
import com.deneme.demo.repos.StockRepository;
import com.deneme.demo.requests.SoftDeleteRequest;

@Service
public class StockService {
	
	private StockRepository stockRepository;
	
	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	public List<Stock> getAllStock(){
		return stockRepository.findAll();
	}
	public Stock getOneStock(Long stockId) {
		return stockRepository.findById(stockId).orElse(null);
	}
	public Stock saveOneStock(Stock newStock) {
		return stockRepository.save(newStock);
	}
	public void deleteStock(Long stockId) {
		stockRepository.deleteById(stockId);
	}
	public Stock softDeleteStock(Long stockId, SoftDeleteRequest deleteRequest) {
		Optional<Stock> stock = stockRepository.findById(stockId);
		if(stock.isPresent())
		{
			stock.get().setDeleted(deleteRequest.isDeleted());
			stock.get().setUpdatedDate(deleteRequest.getUpdatedDate());
			Stock updatedStock = stockRepository.save(stock.get());
			return updatedStock;
		}
		else {
			return null;
		}
	}
}
