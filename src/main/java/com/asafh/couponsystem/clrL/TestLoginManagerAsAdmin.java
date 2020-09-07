package com.asafh.couponsystem.clrL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.Service.AdminService;
import com.asafh.couponsystem.Service.ClientService;
import com.asafh.couponsystem.sequrity.ClientType;
import com.asafh.couponsystem.sequrity.LoginManager;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintLines;
import com.asafh.couponsystem.utils.PrintTitles;

import lombok.experimental.PackagePrivate;

@Component
@Order(6)
public class TestLoginManagerAsAdmin implements CommandLineRunner {

	@Autowired
	private LoginManager loginManager;

	
	private AdminService adminService=null;

	@Override
	public void run(String... args) throws Exception {
		
		PrintTitles.LoginAdmin();
		
		//Test bad login
		System.out.println("Test bad login");
		try {
			adminService = (AdminService) loginManager.Login("admim@admin.com", "admin", ClientType.Administrator);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		PrintLines.printOneLine();
		//Test good login
		System.out.println("Test good login");
		try {
			adminService = (AdminService) loginManager.Login("admin@admin.com", "admin", ClientType.Administrator);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		BeautyTable.tableWithLinesCompanies(adminService.getAllCompanies());
		BeautyTable.tableWithLinesCustomers(adminService.getAllCustomers());
		
	}

}
