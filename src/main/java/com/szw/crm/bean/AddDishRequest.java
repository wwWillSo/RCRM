package com.szw.crm.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class AddDishRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dishId ;
	private String dishName ;
	private String dishType ;
	private BigDecimal price ;
	public int getDishId() {
		return dishId;
	}
	public void setDishId(int dishId) {
		this.dishId = dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getDishType() {
		return dishType;
	}
	public void setDishType(String dishType) {
		this.dishType = dishType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
