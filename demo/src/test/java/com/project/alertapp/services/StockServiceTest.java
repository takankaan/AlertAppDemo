package com.project.alertapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.alertapp.entities.StockModel;
import com.project.alertapp.repos.StockRepository;
import com.project.alertapp.requests.SoftDeleteRequest;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class StockServiceTest {
	
	@InjectMocks
	private StockService stockService;
	
	@Mock
	private StockRepository stockRepository;
	
	
	private SoftDeleteRequest softDeleteRequest = new SoftDeleteRequest();
	private StockModel stock = new StockModel();
	private String ticker;
	@Mock
	private Stock yahooStock;
	
	@BeforeAll
	public void setup() {
		ticker = "TEST";
		stock.setStockSymbol(ticker);
		stock.setId(1L);
		stock.setDeleted(false);
		softDeleteRequest.setDeleted(true);
	} 
	
	@Test
	public void getAllUserTest() {
		stockService.getAllStocks();
		verify(stockRepository,times(1)).findByDeletedFalse();
	}
	
	@Test
	public void getOneStockNullValue() {
		assertEquals(null,stockService.getOneStock(null));
		verify(stockRepository,times(0)).findByStockSymbol(null);
	}
	@Test
	public void getOneStockWrongValue() {
		when(stockRepository.findByStockSymbol("HATA")).thenReturn(null);
		assertEquals(null,stockService.getOneStock("HATA"));
		verify(stockRepository,times(1)).findByStockSymbol("HATA");
	}
	@Test
	public void getOneStockTrueValue() {
		when(stockRepository.findByStockSymbol(ticker)).thenReturn(stock);
		assertEquals(null,stockService.getOneStock("HATA"));
		verify(stockRepository,times(1)).findByStockSymbol("HATA");
	}
	@Test
	public void deleteStockNullValue() {
		stockService.deleteStock(null);
		verify(stockRepository, times(0)).deleteById(stock.getId());
	}
	@Test
	public void deleteStockWrongValue() {
		when(stockRepository.findByStockSymbol("HATA")).thenReturn(null);
		stockService.deleteStock("HATA");
		verify(stockRepository, times(0)).deleteById(stock.getId());
	}
	@Test
	public void deleteStockTrueValue() {
		when(stockRepository.findByStockSymbol(ticker)).thenReturn(stock);
		stockService.deleteStock(ticker);
		verify(stockRepository, times(1)).deleteById(stock.getId());
	}
	@Test
	public void softDeleteNullValue() {
		when(stockRepository.findByStockSymbol(null)).thenReturn(null);
		assertEquals(null,stockService.softDeleteStock(null,softDeleteRequest));
		verify(stockRepository, times(0)).save(null);
	}
	@Test
	public void softDeleteWrongValue() {
		when(stockRepository.findByStockSymbol(ticker)).thenReturn(null);
		assertEquals(null,stockService.softDeleteStock(ticker,softDeleteRequest));
		verify(stockRepository, times(0)).save(null);
	}
	@Test
	public void softDeleteTrueValue() {
		when(stockRepository.findByStockSymbol(ticker)).thenReturn(stock);
		when(stockRepository.save(stock)).thenReturn(stock);
		assertEquals(true,stockService.softDeleteStock(ticker,softDeleteRequest).isDeleted());
		verify(stockRepository, times(1)).save(stock);
	}
	@Test
	public void saveOneStockNullValue() {
		assertEquals(null,stockService.saveOneStock(null));
		verify(stockRepository,times(0)).save(null);
	}
	@Test
	public void saveOneStockExistsValue() {
		when(stockRepository.findByStockSymbol(ticker)).thenReturn(stock);
		assertEquals(null,stockService.saveOneStock(ticker));
		verify(stockRepository,times(0)).save(null);
	}
	@Test
	public void saveOneStockNullStockValue() {
		when(stockRepository.findByStockSymbol(ticker)).thenReturn(null);
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(null);
	        assertEquals(null,stockService.saveOneStock(ticker));
		}
	}
	@Test
	public void saveOneStockTrueValue() {
		when(stockRepository.findByStockSymbol(ticker)).thenReturn(null);
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(yahooStock);
	        when(yahooStock.getName()).thenReturn("TEST STOCK");
	        when(yahooStock.getSymbol()).thenReturn("TEST");
	        when(stockRepository.save(stock)).thenReturn(stock);
	        assertEquals(stock,stockService.saveOneStock(ticker));
	        verify(stockRepository, times(1)).save(stock);
		}
	}
	@Test
	public void saveStocksToDatabaseNullYahooStockValue() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(null);
	        assertEquals(0,stockService.saveStocksToDatabase().size());
		}
	}
	@Test
	public void saveStocksToDatabaseTrueValue() {
		try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
	        utilities.when(() -> YahooFinance.get(ticker))
	          .thenReturn(yahooStock);
	        when(stockService.saveOneStock(ticker)).thenReturn(stock);
	        assertEquals(30,stockService.saveStocksToDatabase().size());
		}
	}

}
