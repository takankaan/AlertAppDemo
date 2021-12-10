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
import com.deneme.demo.response.StockIdAndPrice;


import yahoofinance.YahooFinance;

@Component
public class UpdateStocksValues{
	@Autowired
	AlertsService alertsService;
	@Autowired
	StockRepository stockRepository;
	private StockIdAndPrice stockIdAndPrice;
	private List<StockIdAndPrice> stockIdAndPriceList = new ArrayList<StockIdAndPrice>();
	
	@Scheduled(fixedRate = 15000L)
	public void updateStocks() {
			try{
				stockIdAndPriceList.clear();
				for(StockModel stockModel: stockRepository.findByDeletedFalse())
				{
					String ticker = stockModel.getStockSymbol();
					stockIdAndPrice = new StockIdAndPrice();
					stockIdAndPrice.setCurrentPrice(YahooFinance.get(ticker).getQuote().getPrice());
					stockIdAndPrice.setStockId(stockModel.getId());
					stockIdAndPriceList.add(stockIdAndPrice);
				}
				alertsService.alertChecker(stockIdAndPriceList);
			}
			catch(IOException e){
				System.out.println(e);
			}
	}
	@Configuration
	@EnableScheduling
	class SchedulingConfiguration{}
}
