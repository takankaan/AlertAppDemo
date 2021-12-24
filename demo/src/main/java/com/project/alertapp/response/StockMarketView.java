package com.project.alertapp.response;

import java.math.BigDecimal;

import lombok.Data;
/**
 * This entity is designed for the page where all stocks will be displayed. It contains stock name, stock symbol, current price and change percent values.
 * @author KaanSarigul
 *
 */
@Data
public class StockMarketView {
	
	private String stockName;
	private String stockSymbol;
	private BigDecimal currentPrice;
	private BigDecimal changePercent;
}
