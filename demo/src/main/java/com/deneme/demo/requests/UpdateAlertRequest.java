package com.deneme.demo.requests;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class UpdateAlertRequest {
	
	private BigDecimal alertPrice;
	private boolean alertDirection; 
	private Date updatedDate = new Date(System.currentTimeMillis());

}