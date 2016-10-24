package com.szw.crm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.szw.crm.entity.Complaint;
import com.szw.crm.entity.Customer;

public interface ComplaintRepository extends PagingAndSortingRepository<Complaint, Integer>{

}
