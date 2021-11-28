package com.deneme.demo.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class StockModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String stockName;
	String stockSymbol;
	BigDecimal currentValue;
	boolean deleted = false;
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	Date createdDate = new Date(System.currentTimeMillis());
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	Date updatedDate = createdDate;
	
}
