package com.asafh.couponsystem.clrR;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.EnumType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.repo.CompanyRepository;
import com.asafh.couponsystem.repo.CouponRepository;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintTitles;



//@Component
//@Order(2)
public class TestCouponRepo implements CommandLineRunner{

	public Date realDate(int dd,int mm,int yyyy) {
		return new Date(yyyy-1900,mm-1,dd+1);
	}
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	@Override
	public void run(String... args) throws Exception {
		
//		PrintTitles.coupons();
		
		Company comp1 = new Company();
		comp1.setName("Osem");
		comp1.setEmail("osem@gmail.com");
		comp1.setPassword("123458");
		companyRepository.save(comp1);
		
		
		
		Coupon c1=new Coupon();
		c1.setCompanyID(comp1.getId());
		c1.setCategory(Category.Vacation);
		c1.setTitle("aaa");
		c1.setDescription("bla bla....");
		c1.setStartDate(realDate(27, 8, 2020));
		c1.setEndDate(realDate(27, 9, 2020));
		c1.setAmount(100);
		c1.setPrice(10.9);
		c1.setImage("http://abcdefg....");
		

		Coupon c2=new Coupon();
		c2.setCompanyID(comp1.getId());
		c2.setCategory(Category.Food);
		c2.setTitle("bbb");
		c2.setDescription("bla bla bla....");
		c2.setStartDate(realDate(28, 8, 2020));
		c2.setEndDate(realDate(28, 9, 2020));
		c2.setAmount(80);
		c2.setPrice(11.9);
		c2.setImage("http://abcdefghijk....");
		
		
		Coupon c3=new Coupon();
		c3.setCompanyID(comp1.getId());
		c3.setCategory(Category.Restaurant);
		c3.setTitle("ccc");
		c3.setDescription("bla bla bly....");
		c3.setStartDate(realDate(29, 8, 2020));
		c3.setEndDate(realDate(29, 9, 2020));
		c3.setAmount(90);
		c3.setPrice(13.9);
		c3.setImage("http://abcdefghijklmnop....");
		
		

		comp1.setCoupons(Arrays.asList(c1,c2,c3));
		
		couponRepository.save(c1);
		couponRepository.save(c2);
		couponRepository.save(c3);
		
		
		BeautyTable.tableWithLinesCoupons(couponRepository.findAll());
		
		
	}

}
