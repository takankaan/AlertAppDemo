package com.deneme.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Alerts {

	@Id
	Long id;
	
	Long userId;
	
	Long stockId;
	
	float alertPrice;
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	Date createdDate = new Date(System.currentTimeMillis());
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	Date updatedDate = createdDate; 
	boolean alertDirection;
	boolean deleted = false;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public float getAlertPrice() {
		return alertPrice;
	}
	public void setAlertPrice(float alertPrice) {
		this.alertPrice = alertPrice;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isAlertDirection() {
		return alertDirection;
	}
	public void setAlertDirection(boolean alertDirection) {
		this.alertDirection = alertDirection;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	
	
}
