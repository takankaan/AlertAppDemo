package com.project.alertapp.response;

import java.math.BigDecimal;

import lombok.Data;

/**
 * This entity is designed for control all alerts belongs to a stock. It contains stock symbol and current price values.
 * @author KaanSarigul
 *
 */
@Data
public class StockSymbolAndPrice {
		private String stockSymbol;
		private BigDecimal currentPrice; 
		
}
