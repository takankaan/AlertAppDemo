package com.project.alertapp.requests;

import java.util.Date;

import lombok.Data;

/**
 * This entity designed for soft delete processes. It contains delete value and updatedDate properties.
 * @author KaanSarigul
 *
 */
@Data
public class SoftDeleteRequest {
	
	private boolean deleted;
	private Date updatedDate = new Date(System.currentTimeMillis());
	
}
