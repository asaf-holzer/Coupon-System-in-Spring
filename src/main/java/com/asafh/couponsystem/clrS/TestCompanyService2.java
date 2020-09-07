package com.asafh.couponsystem.clrS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.Service.AdminService;
import com.asafh.couponsystem.Service.CompanyService;
import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.repo.CompanyRepository;
import com.asafh.couponsystem.repo.CouponRepository;
import com.asafh.couponsystem.repo.CustomerRepository;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintTitles;

@Component
@Order(5)
public class TestCompanyService2 implements CommandLineRunner{


	@Autowired
	private CompanyService companyService;

	@Autowired
	private CouponRepository couponRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
	
		PrintTitles.companyService2();
		
		//try delete coupon after purchased by customer
		List<Coupon> allCoupons = couponRepository.findAll();
		BeautyTable.tableWithLinesCoupons(allCoupons);
		companyService.deleteCoupon(3);
		allCoupons=couponRepository.findAll();
		BeautyTable.tableWithLinesCoupons(allCoupons);
		
//		System.err.println("TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST !!!!!!!!!!");
//		System.out.println(companyService.getCompany_Coupons());
		
	}

}
