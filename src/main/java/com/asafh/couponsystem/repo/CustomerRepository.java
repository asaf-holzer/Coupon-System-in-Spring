package com.asafh.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Modifying
	@Query(value= "DELETE FROM couponsystem.customer_coupons WHERE coupons_id = ?1 ", nativeQuery = true)
	void deleteFromCustomer_coupons(int id);
	
	public Customer findByEmailAndPassword(String email, String password);
	
	boolean existsByEmailAndPassword(String email, String password);


}
