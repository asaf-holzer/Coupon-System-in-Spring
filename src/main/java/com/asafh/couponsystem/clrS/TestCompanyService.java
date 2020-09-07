package com.asafh.couponsystem.clrS;

import java.sql.Date;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.Service.CompanyService;
import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.beans.Customer;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintLines;
import com.asafh.couponsystem.utils.PrintTitles;

import antlr.Lookahead;

@Component
@Order(2)
public class TestCompanyService implements CommandLineRunner {

	public Date realDate(int dd, int mm, int yyyy) {
		return new Date(yyyy - 1900, mm - 1, dd + 1);
	}

	@Autowired
	private CompanyService companyService;

	@Override
	public void run(String... args) throws Exception {
		
		PrintTitles.companyService();

		Coupon coupon1 = new Coupon();
		coupon1.setCompanyID(1);
		coupon1.setCategory(Category.Vacation);
		coupon1.setTitle("aaa");
		coupon1.setDescription("bla bla....");
		coupon1.setStartDate(realDate(27, 8, 2020));
		coupon1.setEndDate(realDate(27, 9, 2020));
		coupon1.setAmount(100);
		coupon1.setPrice(10.9);
		coupon1.setImage("http://abcdefg....");

		Coupon coupon2 = new Coupon();
		coupon2.setCompanyID(2);
		coupon2.setCategory(Category.Food);
		coupon2.setTitle("bbb");
		coupon2.setDescription("bla bla bla....");
		coupon2.setStartDate(realDate(28, 8, 2020));
		coupon2.setEndDate(realDate(28, 9, 2020));
		coupon2.setAmount(80);
		coupon2.setPrice(11.9);
		coupon2.setImage("http://abcdefghijk....");

		Coupon coupon3 = new Coupon();
		coupon3.setCompanyID(1);
		coupon3.setCategory(Category.Restaurant);
		coupon3.setTitle("ccc");
		coupon3.setDescription("bla bla bly....");
		coupon3.setStartDate(realDate(29, 8, 2020));
		coupon3.setEndDate(realDate(29, 9, 2020));
		coupon3.setAmount(90);
		coupon3.setPrice(13.9);
		coupon3.setImage("http://abcdefghijklmnop....");

		Coupon coupon4 = new Coupon();
		coupon4.setCompanyID(2);
		coupon4.setCategory(Category.Electricity);
		coupon4.setTitle("ddd");
		coupon4.setDescription("blabla bla bly....");
		coupon4.setStartDate(realDate(29, 9, 2020));
		coupon4.setEndDate(realDate(29, 10, 2020));
		coupon4.setAmount(20);
		coupon4.setPrice(11.9);
		coupon4.setImage("http://asaf....");

		Coupon coupon5 = new Coupon();
		coupon5.setCompanyID(1);
		coupon5.setCategory(Category.Restaurant);
		coupon5.setTitle("ccc");
		coupon5.setDescription("bla bla bly bly....");
		coupon5.setStartDate(realDate(16, 8, 2020));
		coupon5.setEndDate(realDate(16, 9, 2020));
		coupon5.setAmount(200);
		coupon5.setPrice(30.9);
		coupon5.setImage("http://abcdefghijklmnop....");

		Coupon coupon6 = new Coupon();
		coupon6.setCompanyID(3);
		coupon6.setCategory(Category.Vacation);
		coupon6.setTitle("ccc");
		coupon6.setDescription("bla bly bla bly....");
		coupon6.setStartDate(realDate(1, 8, 2020));
		coupon6.setEndDate(realDate(15, 9, 2020));
		coupon6.setAmount(190);
		coupon6.setPrice(3.9);
		coupon6.setImage("http://abcdefghijklmnop....");

		Coupon coupon7 = new Coupon();
		coupon7.setCompanyID(4);
		coupon7.setCategory(Category.Vacation);
		coupon7.setTitle("abc");
		coupon7.setDescription("bla bla....");
		coupon7.setStartDate(realDate(27, 8, 2020));
		coupon7.setEndDate(realDate(27, 9, 2020));
		coupon7.setAmount(110);
		coupon7.setPrice(18.9);
		coupon7.setImage("http://abcdefgou....");

		Coupon coupon8 = new Coupon();
		coupon8.setCompanyID(4);
		coupon8.setCategory(Category.Food);
		coupon8.setTitle("rty");
		coupon8.setDescription("bla bla....");
		coupon8.setStartDate(realDate(27, 8, 2020));
		coupon8.setEndDate(realDate(27, 9, 2020));
		coupon8.setAmount(20);
		coupon8.setPrice(2.9);
		coupon8.setImage("http://abcdefgrrrr....");

		Coupon coupon9 = new Coupon();
		coupon9.setCompanyID(6);
		coupon9.setCategory(Category.Restaurant);
		coupon9.setTitle("title");
		coupon9.setDescription("bla bla....");
		coupon9.setStartDate(realDate(27, 8, 2020));
		coupon9.setEndDate(realDate(27, 9, 2020));
		coupon9.setAmount(5);
		coupon9.setPrice(4.9);
		coupon9.setImage("http://abcdefgwwww....");


		companyService.setCompanyID(1);
		companyService.addCoupon(coupon1);
		companyService.addCoupon(coupon3);

		// try to add coupon with the same title
		System.out.println("try to add coupon with the same title");
		try {
			companyService.addCoupon(coupon5);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the coupons of compeny #" + companyService.getCompanyID());
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons());

		companyService.setCompanyID(2);
		System.out.println("the company id is: " + companyService.getCompanyID());
		companyService.addCoupon(coupon2);
		companyService.addCoupon(coupon4);

		System.out.println("the coupons of compeny #" + companyService.getCompanyID());
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons());

		System.out.println("add coupon with the same title but for anoher company");
		companyService.setCompanyID(3);
		companyService.addCoupon(coupon6);
		
		System.out.println("the coupons of compeny #" + companyService.getCompanyID());
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons());

		
		companyService.setCompanyID(4);
		companyService.addCoupon(coupon7);
		companyService.addCoupon(coupon8);
		
		companyService.setCompanyID(6);
		companyService.addCoupon(coupon9);
				
		// try to update coupon details
		PrintLines.printBoldLine();
		System.out.println("try to update coupon details");
		PrintLines.printBoldLine();
		companyService.setCompanyID(1);
		System.out.println("try to update coupon id");
		coupon1.setId(18);
		try {
			companyService.updateCoupon(1, coupon1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		Thread.sleep(500);
		coupon1.setId(1);
		coupon1.setCompanyID(18);
		System.out.println("try to update coupon companyID");
		try {
			companyService.updateCoupon(1, coupon1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		coupon1.setCompanyID(1);
		coupon1.setAmount(45);
		coupon1.setCategory(Category.Food);
		coupon1.setDescription("abcdefg");
		coupon1.setImage("http....");
		coupon1.setPrice(10000.9);
		coupon1.setTitle("wert");
		coupon1.setStartDate(realDate(21, 8, 2020));
		coupon1.setEndDate(realDate(21, 9, 2020));

		companyService.updateCoupon(1, coupon1);
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons());

//		**********************************************************************
//		Delete coupon Look after customer buy coupons
//		**********************************************************************

		PrintLines.printBoldLine();
		System.out.println("Delete coupon Look after customer buy coupons");
		PrintLines.printBoldLine();

		// try the get all coupons method
		System.out.println("try the get all coupons method");
		PrintLines.printOneLine();
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons());
		PrintLines.printBoldLine();

		// try the get all coupons method sort by category
		System.out.println("try the get all coupons method sort by category type:food");
		PrintLines.printOneLine();
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons(Category.Food));
		PrintLines.printBoldLine();

		// try the get all coupons method sort by max price
		System.out.println("try the get all coupons method sort by max price: 20.9 ");
		PrintLines.printOneLine();
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons(20.9));
		PrintLines.printBoldLine();
		
		//try get company details method
		System.out.println("try get company details method");
		System.out.println(companyService.getcompanyDetails());
		Thread.sleep(500);

	}

}
