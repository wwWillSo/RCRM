package com.szw.crm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.szw.crm.entity.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

	public Customer findByMobile(String mobile) ;
	
	public Customer findById(int id) ;
}
