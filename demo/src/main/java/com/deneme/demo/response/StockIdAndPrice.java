package com.deneme.demo.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockIdAndPrice {
	private Long stockId;
	private BigDecimal currentPrice; 
	
}
