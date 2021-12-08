package com.deneme.demo.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockMarketView {
	
	private String stockName;
	private String stockSymbol;
	private BigDecimal currentPrice;
	private BigDecimal changePercent;
}
