package com.project.alertapp.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import yahoofinance.histquotes.HistoricalQuote;

@Data
public class StockValues {
	private String name;
	private String symbol;
	private BigDecimal currentPrice;
	private BigDecimal changePercent;
	private List<HistoricalQuote> stockHistoryList;
	
}
