package com.deneme.demo.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private StockRepository stockRepository;
	
	
	public StockValues getOneStock(String ticker) {
		try {
			calendar = Calendar.getInstance();
			yahooStock = YahooFinance.get(ticker);
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			List<HistoricalQuote> array = yahooStock.getHistory(calendar,Interval.DAILY);
			stockValues.setName(yahooStock.getName());
			stockValues.setChangePercent(yahooStock.getQuote().getChangeInPercent());
			stockValues.setCurrentPrice(yahooStock.getQuote().getPrice());
			stockValues.setSymbol(yahooStock.getSymbol());
			stockValues.setStockHistoryList(array);
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
