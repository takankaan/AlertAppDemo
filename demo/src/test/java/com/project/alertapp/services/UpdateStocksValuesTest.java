package com.project.alertapp.services;

/*import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.alertapp.entities.StockModel;
import com.project.alertapp.repos.StockRepository;
import com.project.alertapp.response.StockSymbolAndPrice;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest*/
public class UpdateStocksValuesTest {
	/*@InjectMocks
	private UpdateStocksValues updatesStocksValues;
	@Mock
	private StockRepository stockRepository;
	@Mock
	private AlertsService alertsService;
	@Mock
	private StockModel stockModel;
	@Mock
	private Stock yahooStock;
	@Mock
	private StockQuote stockQuote;
	private List<StockSymbolAndPrice> stockSymbolAndPriceList = new ArrayList<StockSymbolAndPrice>();
	private List<StockModel> stockModelList = new ArrayList<StockModel>();

	@Test*/
	public void updateStocksTest() {
		
	/*	stockModelList.add(stockModel);
		when(stockRepository.findByDeletedFalse()).thenReturn(stockModelList);
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get("TEST"))
	          .thenReturn(yahooStock);
		when(stockModel.getStockSymbol()).thenReturn("TEST");
		when(yahooStock.getQuote()).thenReturn(stockQuote);
		when(stockQuote.getPrice()).thenReturn(null);
		verify(alertsService, times(1)).stockControl(stockSymbolAndPriceList);
	}*/
	}
	
}
