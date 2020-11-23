package com.asafh.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asafh.couponsystem.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsByEmailAndPassword(String email, String password);
	
	Company findByEmailAndPassword(String email, String password); 

}
