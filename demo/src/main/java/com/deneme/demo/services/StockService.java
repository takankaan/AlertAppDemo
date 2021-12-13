package com.deneme.demo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
	public List<StockModel> getAllStocks(){
		return stockRepository.findByDeletedFalse();
	}
	public StockModel getOneStock(String ticker) {
			return (ticker != null) ? stockRepository.findByStockSymbol(ticker) : null;
	}
	
	public void deleteStock(String ticker) {
		if(ticker != null)
			if(stockRepository.findByStockSymbol(ticker) != null)
			{
				stockRepository.deleteById(stockRepository.findByStockSymbol(ticker).getId());
				System.out.println(ticker +" Stock successfully removed!");
			}
	}
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
	
	/*public List<StockModel> updateAndGetAllStocks(){
		List<String> stockList = Arrays.asList("FENER.IS","GSRAY.IS","BJKAS.IS","TSPOR.IS","THYAO.IS","ASELS.IS","SKBNK.IS","VESTL.IS","SISE.IS","PETKM.IS","PGSUS.IS","TUPRS.IS","ARCLK.IS",
				"TTKOM.IS","OTKAR.IS","ULKER.IS","DOAS.IS","BANVT.IS","MGROS.IS","ECILC.IS","TKNSA.IS","TCELL.IS","KCHOL.IS","FRIGO.IS","TUKAS.IS","YATAS.IS","ENJSA.IS","CCOLA.IS","IHLAS.IS","AYGAZ.IS");
		Date updatedDate = new Date(System.currentTimeMillis());
		List<StockModel> notDeletedStocks = new ArrayList<StockModel>();
		
		for(String stockString : stockList) {
			try {
				if(stockRepository.existsByStockSymbol(stockString))
				{
					StockModel model = stockRepository.findByStockSymbol(stockString);
					if(!model.isDeleted())
					{
						model.setCurrentValue(YahooFinance.get(model.getStockSymbol()).getQuote().getPrice());
						model.setUpdatedDate(updatedDate);
						notDeletedStocks.add(model);
					}
				}
				else
					saveOneStock(stockString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (notDeletedStocks.size() == 0) ? null: notDeletedStocks ;
	}*/
}
