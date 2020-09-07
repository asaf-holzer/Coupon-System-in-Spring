package com.asafh.couponsystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.asafh.couponsystem.beans.Company_Coupons;
import com.asafh.couponsystem.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

	@Modifying
	@Query(value= "DELETE FROM couponsystem.customer_coupons WHERE coupons_id = ?1 ", nativeQuery = true)
	void deleteFromCustomer_coupons(int id);
	
	@Modifying
	@Query(value= "DELETE FROM couponsystem.company_coupons WHERE coupons_id = ?1 ", nativeQuery = true)
	void deleteFromCompany_coupons(int id);
	
//	@Modifying
//	@Query(value= "SELECT * FROM couponsystem.company_coupons")
//	public List<Company_Coupons> getCompany_Coupons();
}
