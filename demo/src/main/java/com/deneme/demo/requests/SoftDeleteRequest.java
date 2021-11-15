package com.deneme.demo.requests;

import java.util.Date;

public class SoftDeleteRequest {
	
	private boolean deleted;
	private Date updatedDate = new Date(System.currentTimeMillis());
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}

	
}
