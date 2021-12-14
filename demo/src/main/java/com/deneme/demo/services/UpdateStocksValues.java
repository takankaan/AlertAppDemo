package com.deneme.demo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deneme.demo.entities.StockModel;
import com.deneme.demo.repos.StockRepository;
import com.deneme.demo.response.StockSymbolAndPrice;


import yahoofinance.YahooFinance;

@Component
public class UpdateStocksValues{
	@Autowired
	AlertsService alertsService;
	@Autowired
	StockRepository stockRepository;
	private StockSymbolAndPrice stockSymbolAndPrice;
	private List<StockSymbolAndPrice> stockSymbolAndPriceList = new ArrayList<StockSymbolAndPrice>();
	
	@Scheduled(fixedRate = 15000L)
	public void updateStocks() {
			try{
				stockSymbolAndPriceList.clear();
				for(StockModel stockModel: stockRepository.findByDeletedFalse())
				{
					String ticker = stockModel.getStockSymbol();
					stockSymbolAndPrice = new StockSymbolAndPrice();
					stockSymbolAndPrice.setCurrentPrice(YahooFinance.get(ticker).getQuote().getPrice());
					stockSymbolAndPrice.setStockSymbol(stockModel.getStockSymbol());
					stockSymbolAndPriceList.add(stockSymbolAndPrice);
				}
				alertsService.stockControl(stockSymbolAndPriceList);
			}
			catch(IOException e){
				System.out.println(e);
			}
	}
	@Configuration
	@EnableScheduling
	class SchedulingConfiguration{}
}
