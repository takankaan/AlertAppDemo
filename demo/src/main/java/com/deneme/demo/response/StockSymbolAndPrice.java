package com.deneme.demo.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockSymbolAndPrice {
		private String stockSymbol;
		private BigDecimal currentPrice; 
		
}
