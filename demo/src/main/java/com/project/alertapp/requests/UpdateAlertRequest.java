package com.project.alertapp.requests;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * This entity designed for update alerts values. It contains price, alert direction, permanent value and updated date value. 
 * @author KaanSarigul
 *
 */
@Data
public class UpdateAlertRequest {
	
	private BigDecimal alertPrice;
	private boolean alertDirection; 
	private boolean permanent;
	private Date updatedDate = new Date(System.currentTimeMillis());

}
