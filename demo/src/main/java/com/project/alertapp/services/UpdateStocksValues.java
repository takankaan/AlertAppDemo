package com.project.alertapp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.alertapp.entities.StockModel;
import com.project.alertapp.repos.StockRepository;
import com.project.alertapp.response.StockSymbolAndPrice;

import yahoofinance.YahooFinance;

/**
 * This component updates stock prices every 15 seconds.
 * @author KaanSarigul
 *
 */
@Component
public class UpdateStocksValues{
	@Autowired
	AlertsService alertsService;
	@Autowired
	StockRepository stockRepository;
	private StockSymbolAndPrice stockSymbolAndPrice;
	private List<StockSymbolAndPrice> stockSymbolAndPriceList = new ArrayList<StockSymbolAndPrice>();
	
	/**
	 * This method automatically updates stock prices every 15 seconds. Also calls the stockControl method to check for alerts with updated prices. 
	 */
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
