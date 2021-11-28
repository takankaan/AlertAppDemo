package com.deneme.demo.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.deneme.demo.entities.StockModel;
import com.deneme.demo.repos.StockRepository;
import com.deneme.demo.requests.SoftDeleteRequest;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class StockService {
	
	private StockRepository stockRepository;
	
	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
/*	public List<StockModel> getAllStock(){
		return stockRepository.findAll();
	}*/
	public StockModel getOneStock(String ticker) {
		return stockRepository.findByStockSymbol(ticker);
	}
	public void deleteStock(String ticker) {
		StockModel stockModel =  stockRepository.findByStockSymbol(ticker);
		if( stockModel != null)
			stockRepository.deleteById(stockModel.getId());
	}
	public StockModel softDeleteStock(String ticker, SoftDeleteRequest deleteRequest) {
		StockModel stock = stockRepository.findByStockSymbol(ticker);
		if(stock != null)
		{
			stock.setDeleted(deleteRequest.isDeleted());
			stock.setUpdatedDate(deleteRequest.getUpdatedDate());
			StockModel updatedStock = stockRepository.save(stock);
			return updatedStock;
		}
		else {
			return null;
		}
	}
	
	public StockModel saveOneStock(String ticker) {
		if(stockRepository.findByStockSymbol(ticker) == null) 
		{
			StockModel stockModel = new StockModel();
			Stock stock;
			try {
				stock = YahooFinance.get(ticker);
				if(stock != null) {
					stockModel.setStockName(stock.getName());
					stockModel.setStockSymbol(stock.getSymbol());
					stockModel.setCurrentValue(stock.getQuote().getPrice());
					return stockRepository.save(stockModel);
				}
			} catch (IOException e) {
				
				System.out.println("VALUE NOT FOUND! : " + e);
			}
		}
		return null;
	}
	
	public List<StockModel> updateAndGetAllStocks(){
		List<String> stockList = Arrays.asList("FENER.IS","GSRAY.IS","BJKAS.IS","TSPOR.IS","THYAO.IS","ASELS.IS","SKBNK.IS","VESTL.IS","SISE.IS","PETKM.IS","PGSUS.IS","TUPRS.IS","ARCLK.IS",
				"TTKOM.IS","OTKAR.IS","ULKER.IS","DOAS.IS","BANVT.IS","MGROS.IS","ECILC.IS","TKNSA.IS","TCELL.IS","KCHOL.IS","FRIGO.IS","TUKAS.IS","YATAS.IS","ENJSA.IS","CCOLA.IS","IHLAS.IS","AYGAZ.IS");
		Date updatedDate = new Date(System.currentTimeMillis());
		StockModel model = null;
		for(String stockString : stockList) {
			try {
				if(stockRepository.existsByStockSymbol(stockString))
				{
					model = stockRepository.findByStockSymbol(stockString);
					model.setCurrentValue(YahooFinance.get(model.getStockSymbol()).getQuote().getPrice());
					model.setUpdatedDate(updatedDate);
				}
				else
					saveOneStock(stockString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stockRepository.findAll();
	}
}
