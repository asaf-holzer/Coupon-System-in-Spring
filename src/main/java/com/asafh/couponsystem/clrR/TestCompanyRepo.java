package com.asafh.couponsystem.clrR;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.repo.CompanyRepository;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintTitles;

//@Component
//@Order(1)
public class TestCompanyRepo implements CommandLineRunner {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public void run(String... args) throws Exception {
		
//		PrintTitles.companies();

		Company c1 = new Company();
		c1.setName("Coca-cola");
		c1.setEmail("coca@cola.com");
		c1.setPassword("12345");
		

		Company c2 = new Company();
		c2.setName("Pepsi");
		c2.setEmail("pepsi@cola.com");
		c2.setPassword("12346");
		

		Company c3 = new Company();
		c3.setName("Alfredo");
		c3.setEmail("alf@redo.com");
		c3.setPassword("12347");
		
		
		companyRepository.save(c1);
		companyRepository.save(c2);
		companyRepository.save(c3);

		BeautyTable.tableWithLinesCompanies(companyRepository.findAll());
		
		System.out.println("get one company #3");
		System.out.println(companyRepository.findById(3));
		
		System.out.println("update company #2");
		c2.setName("Pepsi-cola");
		c2.setEmail("pepsi-cola@gmail.com");
		c2.setPassword("22346");
		
		companyRepository.saveAndFlush(c2);
		System.out.println("companies after update");
		BeautyTable.tableWithLinesCompanies(companyRepository.findAll());
		
		companyRepository.delete(c1);
		System.out.println("delete company #1");
		BeautyTable.tableWithLinesCompanies(companyRepository.findAll());
		
	}

}
