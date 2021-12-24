package com.project.alertapp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.alertapp.entities.StockModel;
import com.project.alertapp.repos.StockRepository;
import com.project.alertapp.requests.SoftDeleteRequest;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * StockService provides receive, update and delete stocks.
 * @author KaanSarigul
 *
 */
@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	
	/**
	 * This method retrieves all undeleted stocks from database.
	 * @return Returns list of all undeleted stocks.
	 */
	public List<StockModel> getAllStocks(){
		return stockRepository.findByDeletedFalse();
	}
	/**
	 * This method retrieves one stock by using ticker.
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 * @return Returns one stockModel.
	 */
	public StockModel getOneStock(String ticker) {
			return (ticker != null) ? stockRepository.findByStockSymbol(ticker) : null;
	}
	/**
	 * This method completely deletes a stock by using ticker.
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 */
	public void deleteStock(String ticker) {
		if(ticker != null)
			if(stockRepository.findByStockSymbol(ticker) != null)
			{
				stockRepository.deleteById(stockRepository.findByStockSymbol(ticker).getId());
				System.out.println(ticker +" Stock successfully removed!");
			}
	}
	/**
	 * This method changes deleted property of the stock. It means soft delete a stock. 
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 * @param deleteRequest It contains deleted property which will change stock's deleted property.
	 * @return Returns updated stock.
	 */
	public StockModel softDeleteStock(String ticker, SoftDeleteRequest deleteRequest) {
		if(ticker != null && deleteRequest !=null)
		{
			StockModel stock = stockRepository.findByStockSymbol(ticker);
			if(stock != null)
			{
				stock.setDeleted(deleteRequest.isDeleted());
				stock.setUpdatedDate(deleteRequest.getUpdatedDate());
				StockModel updatedStock = stockRepository.save(stock);
				return updatedStock;
			}
			else
				return null;
		}else 
			return null;
	}
	/**
	 * This method provides to create a new stock by using ticker.
	 * @param ticker Ticker uses as stock symbol which retrieve stock data from yahooFinance.
	 * @return Returns saved stock.
	 */
	public StockModel saveOneStock(String ticker) {
		if(ticker != null)
		{
			if(stockRepository.findByStockSymbol(ticker) == null ) 
			{
				try {
					Stock stock = YahooFinance.get(ticker);
					if(stock != null) 
					{
						StockModel stockModel = new StockModel();
						stockModel.setStockName(stock.getName());
						stockModel.setStockSymbol(stock.getSymbol());
						return stockRepository.save(stockModel);
					}
				} 
				catch (IOException e){
					System.out.println("VALUE NOT FOUND! : " + e);
				}
			}
		}
		return null;
	}
	/**
	 * This method saves the stocks to the database by using the list in it. 
	 * @return Returns List of StockModels.
	 */
	public List<StockModel> saveStocksToDatabase(){
		List<String> stockList = Arrays.asList("FENER.IS","GSRAY.IS","BJKAS.IS","TSPOR.IS","THYAO.IS","ASELS.IS","SKBNK.IS","VESTL.IS","SISE.IS","PETKM.IS","PGSUS.IS","TUPRS.IS","ARCLK.IS",
				"TTKOM.IS","OTKAR.IS","ULKER.IS","DOAS.IS","BANVT.IS","MGROS.IS","ECILC.IS","TKNSA.IS","TCELL.IS","KCHOL.IS","FRIGO.IS","TUKAS.IS","YATAS.IS","ENJSA.IS","CCOLA.IS","IHLAS.IS","AYGAZ.IS");
		List<StockModel> stockModelList = new ArrayList<StockModel>();
		for(String modelTicker : stockList)
		{
			try {
				if(YahooFinance.get(modelTicker) != null)
				stockModelList.add(saveOneStock(modelTicker));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stockModelList;
	}
	
	
}
