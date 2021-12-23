package com.project.alertapp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.alertapp.entities.StockModel;
import com.project.alertapp.repos.StockRepository;
import com.project.alertapp.response.StockMarketView;
import com.project.alertapp.response.StockValues;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * This service includes methods that return values of one stock or all stocks. 
 * @author KaanSarigul
 *
 */
@Service
public class StockValueService {
	private StockValues stockValues = new StockValues();
	private Calendar calendar;
	private Stock yahooStock;
	private Stock yahooStockForAll;
	private Interval interval;
	
	@Autowired
	private StockRepository stockRepository;
	
	/**
	 * This method returns the stock values specified in the ticker using yahooFinance API. 
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 * @param dataMode This parameter specifies the time period which the data will be displayed.
	 * @return Returns the data of a stock in StockValues type.
	 */
	public StockValues getOneStock(String ticker, Optional<String> dataMode) {
		if(ticker != null && dataMode != null)
		{
			calendar = Calendar.getInstance();
			if(dataMode.isPresent()) {
				switch(dataMode.get()) {
				case "month": calendar.add(Calendar.DATE, -30); interval = Interval.DAILY;break;
				case "3months": calendar.add(Calendar.DATE, -90);interval = Interval.WEEKLY;break;
				case "year": calendar.add(Calendar.DAY_OF_YEAR, -365);interval = Interval.MONTHLY; break;
				default :	calendar.add(Calendar.DATE, -7); interval = Interval.DAILY; break;
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
		}else
			return null;
	}
	/**
	 * This method retrieves all stocks with current price and change percent.
	 * @return Returns list of StockMarketView which has current price, stock name and change percent.
	 */
	public List<StockMarketView> getAllStocks()
	{
		List<StockMarketView> stockMarketViewList = new ArrayList<StockMarketView>();
		StockMarketView stockMarketView;
		List<StockModel> stockList = stockRepository.findByDeletedFalse();
		try {
		for(StockModel stockModel : stockList) {
			stockMarketView = new StockMarketView();
			yahooStockForAll = YahooFinance.get(stockModel.getStockSymbol());
			stockMarketView.setStockSymbol(yahooStockForAll.getSymbol());
			stockMarketView.setStockName(yahooStockForAll.getName());
			stockMarketView.setCurrentPrice(yahooStockForAll.getQuote().getPrice());
			stockMarketView.setChangePercent(yahooStockForAll.getQuote().getChangeInPercent());
			stockMarketViewList.add(stockMarketView);
		}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return stockMarketViewList;
	}
}
