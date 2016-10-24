package com.szw.crm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.szw.crm.entity.DishMenu;

public interface DishMenuRepository extends PagingAndSortingRepository<DishMenu, Integer>{

	public DishMenu findByDishType(String dishType) ;
	
}
