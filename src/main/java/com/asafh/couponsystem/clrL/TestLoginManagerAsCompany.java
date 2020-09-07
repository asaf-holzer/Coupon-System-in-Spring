package com.asafh.couponsystem.clrL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.Service.AdminService;
import com.asafh.couponsystem.Service.CompanyService;
import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.sequrity.ClientType;
import com.asafh.couponsystem.sequrity.LoginManager;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintLines;
import com.asafh.couponsystem.utils.PrintTitles;
@Component
@Order(7)
public class TestLoginManagerAsCompany implements CommandLineRunner {

	@Autowired
	private LoginManager loginManager;

	
	private CompanyService companyService=null;

	@Override
	public void run(String... args) throws Exception {
		
		PrintTitles.LoginCompany();
		
		//Test bad login
		System.out.println("Test bad login");
		try {
			companyService = (CompanyService) loginManager.Login("pepsi@cola.com", "1234", ClientType.Company);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		PrintLines.printOneLine();
		//Test good login
		System.out.println("Test good login");
		try {
			companyService = (CompanyService) loginManager.Login("pepsi@cola.com", "12346", ClientType.Company);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons());
		BeautyTable.tableWithLinesCoupons(companyService.getCompanyCoupons(Category.Food));
	}	
}
