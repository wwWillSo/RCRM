package com.szw.crm.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.szw.crm.entity.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer>{

	public List<Order> findByCustomerId(Integer customerId) ;
	
}
