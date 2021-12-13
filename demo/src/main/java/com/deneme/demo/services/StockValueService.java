package com.deneme.demo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deneme.demo.entities.StockModel;
import com.deneme.demo.repos.StockRepository;
import com.deneme.demo.response.StockMarketView;
import com.deneme.demo.response.StockValues;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Service
public class StockValueService {
	private StockValues stockValues = new StockValues();
	private Calendar calendar;
	private Stock yahooStock;
	private Interval interval;
	@Autowired
	private StockRepository stockRepository;
	
	
	public StockValues getOneStock(String ticker, Optional<String> dataMode) {
		calendar = Calendar.getInstance();
		if(dataMode.isPresent()) {
			switch(dataMode.get()) {
			case "month": calendar.add(Calendar.DATE, -30); interval = Interval.DAILY;break;
			case "3months": calendar.add(Calendar.DATE, -90);interval = Interval.WEEKLY;break;
			case "year": calendar.add(Calendar.DAY_OF_YEAR, -365);interval = Interval.MONTHLY; break;
			}
		}
		else {
			calendar.add(Calendar.DATE, -7); 
			interval = Interval.DAILY;
		}
		try {
			
			yahooStock = YahooFinance.get(ticker);
			if(yahooStock != null)
			{
				if(stockRepository.findByStockSymbol(ticker) != null)
				{
					List<HistoricalQuote> array = yahooStock.getHistory(calendar,interval);
					stockValues.setName(yahooStock.getName());
					stockValues.setChangePercent(yahooStock.getQuote().getChangeInPercent());
					stockValues.setCurrentPrice(yahooStock.getQuote().getPrice());
					stockValues.setSymbol(yahooStock.getSymbol());
					stockValues.setStockHistoryList(array);
				}
				else
					return null;
			}
			else
				return null;
		}catch(IOException e) {
			System.out.println(e);
		}
		return stockValues;
	}
	
	public List<StockMarketView> getAllStocks()
	{
		List<StockMarketView> stockMarketViewList = new ArrayList<StockMarketView>();
		StockMarketView stockMarketView;
		List<StockModel> stockList = stockRepository.findByDeletedFalse();
		try {
		for(StockModel stockModel : stockList) {
			stockMarketView = new StockMarketView();
			yahooStock = YahooFinance.get(stockModel.getStockSymbol());
			stockMarketView.setStockSymbol(yahooStock.getSymbol());
			stockMarketView.setStockName(yahooStock.getName());
			stockMarketView.setCurrentPrice(yahooStock.getQuote().getPrice());
			stockMarketView.setChangePercent(yahooStock.getQuote().getChangeInPercent());
			stockMarketViewList.add(stockMarketView);
		}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return stockMarketViewList;
	}
}
