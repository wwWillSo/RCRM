package com.szw.crm.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the dish_menu database table.
 * 
 */
@Entity
@Table(name="dish_menu")
@NamedQuery(name="DishMenu.findAll", query="SELECT d FROM DishMenu d")
public class DishMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="dish_id")
	private int dishId;

	@Column(name="dish_name")
	private String dishName;

	@Column(name="dish_type")
	@Enumerated(EnumType.STRING)
	private DISHTYPE dishType;

	private BigDecimal price;

	public DishMenu() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDishId() {
		return this.dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return this.dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}


	public DISHTYPE getDishType() {
		return dishType;
	}

	public void setDishType(DISHTYPE dishType) {
		this.dishType = dishType;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public enum DISHTYPE {
		中餐 ,
		西餐
	}

}