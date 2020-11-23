package com.asafh.couponsystem.clrL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.Service.CustomerService;
import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.repo.CouponRepository;
import com.asafh.couponsystem.sequrity.ClientType;
import com.asafh.couponsystem.sequrity.LoginManager;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintLines;
import com.asafh.couponsystem.utils.PrintTitles;

//@Component
@Order(8)
public class TestLoginManagerAsCustomer implements CommandLineRunner {

	@Autowired
	private LoginManager loginManager;

	private CustomerService customerService = null;

	@Override
	public void run(String... args) throws Exception {
		
		PrintTitles.LoginCustomer();
		
		// Test bad login
		System.out.println("Test bad login");
		try {
			String token=  loginManager.login("Moshe@Cohen.com", "1234", ClientType.Customer);
//			customerService = (CustomerService) loginManager.login("Moshe@Cohen.com", "1234", ClientType.Customer);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		PrintLines.printOneLine();
		// Test good login
		System.out.println("Test good login");
		try {
			String token= loginManager.login("Moshe@Cohen.com", "2345", ClientType.Customer);
//			customerService = (CustomerService) loginManager.login("Moshe@Cohen.com", "2345", ClientType.Customer);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		BeautyTable.tableWithLinesCoupons(customerService.getCustomerCoupons());
		BeautyTable.tableWithLinesCoupons(customerService.getCustomerCoupons(Category.Food));

		PrintTitles.DailyJob();
	
	}
}
