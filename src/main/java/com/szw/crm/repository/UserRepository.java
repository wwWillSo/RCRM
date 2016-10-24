package com.szw.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szw.crm.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}