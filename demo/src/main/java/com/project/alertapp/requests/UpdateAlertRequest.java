package com.project.alertapp.requests;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class UpdateAlertRequest {
	
	private BigDecimal alertPrice;
	private boolean alertDirection; 
	private boolean permanent;
	private Date updatedDate = new Date(System.currentTimeMillis());

}
