package com.project.alertapp.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import yahoofinance.histquotes.HistoricalQuote;

/**
 * This entity is designed to hold all the detailed data of a stock. It contains stock name, stock symbol, current price, change percent and historical datas.
 * @author KaanSarigul
 *
 */
@Data
public class StockValues {
	private String name;
	private String symbol;
	private BigDecimal currentPrice;
	private BigDecimal changePercent;
	private List<HistoricalQuote> stockHistoryList;
	
}
