package com.deneme.demo.requests;

import java.util.Date;

import lombok.Data;

@Data
public class SoftDeleteRequest {
	
	private boolean deleted;
	private Date updatedDate = new Date(System.currentTimeMillis());
	
}
