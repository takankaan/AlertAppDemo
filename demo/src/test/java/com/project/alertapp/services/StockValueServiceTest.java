package com.project.alertapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.project.alertapp.response.StockValues;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class StockValueServiceTest{
	
	@InjectMocks
	private StockValueService stockValueService;
	@Mock
	private StockRepository stockRepository;
	@Mock
	private StockQuote stockQuote;
	@Mock
	private Stock yahooStock;
	@Mock
	private Stock yahooStockForAll;
	private StockValues stockValues = new StockValues();
	private String ticker = "TEST";
	private List<StockModel> stockModelList = new ArrayList<StockModel>();
	private StockModel stockModel = new StockModel();
	@BeforeAll
	public void setup() {
		stockModel.setStockSymbol(ticker);
		stockModelList.add(stockModel);
		stockValues.setName("TEST STOCK");
	}
	@Test
	public void getOneStockNullValueTest() {
		assertEquals(null,stockValueService.getOneStock(null,null));
	}
	@Test
	public void getOneStockNotExistsInYahooFinanceValueTest() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(null);
	        assertEquals(null,stockValueService.getOneStock(ticker,Optional.of("month")));
		}catch(Exception ex) {}
	}
	@Test
	public void getOneStockNotExistsInStockRepositoryValueTest() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(yahooStock);
	        when(stockRepository.findByStockSymbol(ticker)).thenReturn(null);
	        assertEquals(null,stockValueService.getOneStock(ticker,Optional.of("month")));
		}catch(Exception ex) {}
	}
	@Test
	public void getOneStockEmptyDataModeValueTest(){
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(yahooStock);
	        when(yahooStock.getName()).thenReturn("TEST STOCK");
	        when(stockRepository.findByStockSymbol(ticker)).thenReturn(new StockModel());
	        when(yahooStock.getQuote()).thenReturn(stockQuote);
	        when(yahooStock.getQuote().getChangeInPercent()).thenReturn(null);
	        when(yahooStock.getSymbol()).thenReturn(null);
	        when(yahooStock.getHistory()).thenReturn(null);
	        when(yahooStock.getQuote().getPrice()).thenReturn(null);
	        assertEquals(stockValues.getName(),stockValueService.getOneStock(ticker,Optional.empty()).getName());
	        verify(yahooStock, times(1)).getName();
		assertEquals(stockValues.getName(),stockValueService.getOneStock(ticker,Optional.empty()).getName());
		}catch(Exception ex) {}
	}
	
	@Test
	public void getOneStockTrueMonthValueTest() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(yahooStock);
	        when(yahooStock.getName()).thenReturn("TEST STOCK");
	        when(stockRepository.findByStockSymbol(ticker)).thenReturn(new StockModel());
	        when(yahooStock.getQuote()).thenReturn(stockQuote);
	        when(yahooStock.getQuote().getChangeInPercent()).thenReturn(null);
	        when(yahooStock.getSymbol()).thenReturn(null);
	        when(yahooStock.getHistory()).thenReturn(null);
	        when(yahooStock.getQuote().getPrice()).thenReturn(null);
	        assertEquals(stockValues.getName(),stockValueService.getOneStock(ticker,Optional.of("month")).getName());
	        verify(yahooStock, times(1)).getName();
		}catch(Exception ex) {}
	}
	@Test
	public void getOneStockTrue3MonthsValueTest() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(yahooStock);
	        when(yahooStock.getName()).thenReturn("TEST STOCK");
	        when(stockRepository.findByStockSymbol(ticker)).thenReturn(new StockModel());
	        when(yahooStock.getQuote()).thenReturn(stockQuote);
	        when(yahooStock.getQuote().getChangeInPercent()).thenReturn(null);
	        when(yahooStock.getSymbol()).thenReturn(null);
	        when(yahooStock.getHistory()).thenReturn(null);
	        when(yahooStock.getQuote().getPrice()).thenReturn(null);
	        assertEquals(stockValues.getName(),stockValueService.getOneStock(ticker,Optional.of("3months")).getName());
	        verify(yahooStock, times(1)).getName();
		}catch(Exception ex) {}
	}
	@Test
	public void getOneStockTrueYearValueTest() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(yahooStock);
	        when(yahooStock.getName()).thenReturn("TEST STOCK");
	        when(stockRepository.findByStockSymbol(ticker)).thenReturn(new StockModel());
	        when(yahooStock.getQuote()).thenReturn(stockQuote);
	        when(yahooStock.getQuote().getChangeInPercent()).thenReturn(null);
	        when(yahooStock.getSymbol()).thenReturn(null);
	        when(yahooStock.getHistory()).thenReturn(null);
	        when(yahooStock.getQuote().getPrice()).thenReturn(null);
	        assertEquals(stockValues.getName(),stockValueService.getOneStock(ticker,Optional.of("year")).getName());
	        verify(yahooStock, times(1)).getName();
		}catch(Exception ex) {}
	}
	@Test
	public void getOneStockTrueDefaultValueTest() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(yahooStock);
	        when(yahooStock.getName()).thenReturn("TEST STOCK");
	        when(stockRepository.findByStockSymbol(ticker)).thenReturn(new StockModel());
	        when(yahooStock.getQuote()).thenReturn(stockQuote);
	        when(yahooStock.getQuote().getChangeInPercent()).thenReturn(null);
	        when(yahooStock.getSymbol()).thenReturn(null);
	        when(yahooStock.getHistory()).thenReturn(null);
	        when(yahooStock.getQuote().getPrice()).thenReturn(null);
	        assertEquals(stockValues.getName(),stockValueService.getOneStock(ticker,Optional.of("test")).getName());
	        verify(yahooStock, times(1)).getName();
		}catch(Exception ex) {}
	}
	@Test
	public void getAllStocksTest() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(stockModel.getStockSymbol()))
	          .thenReturn(yahooStockForAll);
		when(stockRepository.findByDeletedFalse()).thenReturn(stockModelList);
		when(yahooStockForAll.getName()).thenReturn("TEST STOCK");
        when(yahooStockForAll.getQuote()).thenReturn(stockQuote);
        when(yahooStockForAll.getQuote().getChangeInPercent()).thenReturn(null);
        when(yahooStockForAll.getSymbol()).thenReturn(null);
        when(yahooStockForAll.getQuote().getPrice()).thenReturn(null);
        assertEquals(stockModelList.size(), stockValueService.getAllStocks().size());
		}
	}
	
}
