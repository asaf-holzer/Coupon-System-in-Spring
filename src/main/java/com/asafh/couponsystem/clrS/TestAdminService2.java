package com.asafh.couponsystem.clrS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.Service.AdminService;
import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Customer;
import com.asafh.couponsystem.repo.CompanyRepository;
import com.asafh.couponsystem.repo.CouponRepository;
import com.asafh.couponsystem.repo.CustomerRepository;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintLines;
import com.asafh.couponsystem.utils.PrintTitles;

@Component
@Order(4)
public class TestAdminService2 implements CommandLineRunner {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Override
	public void run(String... args) throws Exception {
		
		PrintLines.Print2Lines();
		Thread.sleep(0);
		PrintTitles.adminService2();
		PrintLines.Print2Lines();
		//try delete company after purchased his coupons by customer
		List<Company> allCompanies = companyRepository.findAll();
		BeautyTable.tableWithLinesCompanies(allCompanies);
		adminService.deleteCompany(allCompanies.get(0).getId());
		allCompanies = companyRepository.findAll();
		System.out.println("delete company #1");
		System.out.println("companies after delete");
		BeautyTable.tableWithLinesCompanies(allCompanies);

		//try delete customer after purchased  coupons 
		List<Customer> allCustomers = customerRepository.findAll();
		BeautyTable.tableWithLinesCustomers(allCustomers);
		BeautyTable.tableWithLinesCoupons(couponRepository.findAll());
		adminService.deleteCustomer(allCustomers.get(1).getId());
		allCustomers = customerRepository.findAll();
		System.out.println("delete customer #1");
		System.out.println("customers after delete");
		BeautyTable.tableWithLinesCustomers(allCustomers);
		BeautyTable.tableWithLinesCoupons(couponRepository.findAll());
		
		

	}

}
