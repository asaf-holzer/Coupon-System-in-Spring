package com.asafh.couponsystem.clrS;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.Service.CustomerService;
import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.repo.CouponRepository;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintLines;
import com.asafh.couponsystem.utils.PrintTitles;

@Component
@Order(3)
public class TestCustomerService implements CommandLineRunner {

	public Date realDate(int dd, int mm, int yyyy) {
		return new Date(yyyy - 1900, mm - 1, dd + 1);
	}

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CouponRepository couponRepository;

	@Override
	public void run(String... args) throws Exception {
		
		PrintTitles.customerService();

		List<Coupon> allCoupons = couponRepository.findAll();
		BeautyTable.tableWithLinesCoupons(allCoupons);
		customerService.setCustomerID(1);
		customerService.purchaseCoupon(allCoupons.get(0));
		customerService.purchaseCoupon(allCoupons.get(2));
		customerService.purchaseCoupon(allCoupons.get(4));
		System.out.println("coupons: #1 #3 #5 puchsed");
		System.out.println("look at the amount");
		BeautyTable.tableWithLinesCoupons(allCoupons);

		customerService.setCustomerID(2);
		customerService.purchaseCoupon(allCoupons.get(3));

		// try to buy coupon again
		PrintLines.printBoldLine();
		System.out.println("try to buy coupon again");
		PrintLines.printOneLine();
		try {
			customerService.purchaseCoupon(allCoupons.get(3));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		PrintLines.printBoldLine();
		
		customerService.setCustomerID(3);
		customerService.purchaseCoupon(allCoupons.get(5));
		customerService.purchaseCoupon(allCoupons.get(7));
		customerService.setCustomerID(4);
		customerService.purchaseCoupon(allCoupons.get(5));


		// try to buy coupon with amount 0
		System.out.println("try to buy coupon with amount 0");
		PrintLines.printOneLine();
		allCoupons.get(1).setAmount(0);
		try {
			customerService.purchaseCoupon(allCoupons.get(1));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		PrintLines.printBoldLine();
		// try to buy coupon expired date
		System.out.println("try to buy coupon expired date");
		PrintLines.printOneLine();
		allCoupons.get(1).setAmount(50);
		allCoupons.get(1).setEndDate(realDate(25, 8, 2020));
		try {
			customerService.purchaseCoupon(allCoupons.get(1));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// try to get all customer coupons
		customerService.setCustomerID(1);
		PrintLines.printBoldLine();
		System.out.println("try to get all customer coupons  ");
		PrintLines.printOneLine();
		BeautyTable.tableWithLinesCoupons(customerService.getCustomerCoupons());
		PrintLines.printBoldLine();

		// try to get all customer coupons sort by category
		System.out.println("try to get all customer coupons sort by category: Food");
		PrintLines.printOneLine();
		BeautyTable.tableWithLinesCoupons(customerService.getCustomerCoupons(Category.Food));
		PrintLines.printBoldLine();

		// try to get all customer coupons sort by max price
		System.out.println("try to get all customer coupons sort by max price: 20.0 ");
		PrintLines.printOneLine();
		BeautyTable.tableWithLinesCoupons(customerService.getCustomerCoupons(20.0));
		PrintLines.printBoldLine();
		
		//try get all customer details 
		PrintLines.printBoldLine();
		System.out.println("try get customer details");
		PrintLines.printOneLine();
		System.out.println(customerService.getCustomerDetails());
		
		//try to buy coupon by customer #2 that already purchased by #1
		customerService.setCustomerID(2);
		customerService.purchaseCoupon(allCoupons.get(2));
		System.out.println("the coupons after buy coupon that purchase by another");
		BeautyTable.tableWithLinesCoupons(customerService.getCustomerCoupons());
		PrintLines.printBoldLine();


	}

}
