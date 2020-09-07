package com.asafh.couponsystem.clrR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import com.asafh.couponsystem.beans.Customer;
import com.asafh.couponsystem.repo.CustomerRepository;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintTitles;

//@Component
//@Order(3)
public class TestCustomerRepo implements CommandLineRunner{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
//		PrintTitles.customer();
		
		Customer cust1= new Customer();
		cust1.setFirstName("Moshe");
		cust1.setLastName("Cohen");
		cust1.setEmail("moshe@cohen.com");
		cust1.setPassword("1980");
		
		Customer cust2= new Customer();
		cust2.setFirstName("David");
		cust2.setLastName("Hamelech");
		cust2.setEmail("david@hamelech.com");
		cust2.setPassword("1990");
		
		Customer cust3= new Customer();
		cust3.setFirstName("Noam");
		cust3.setLastName("Lev");
		cust3.setEmail("noam@lev.com");
		cust3.setPassword("1985");
		
		customerRepository.save(cust1);
		customerRepository.save(cust2);
		customerRepository.save(cust3);
		
		BeautyTable.tableWithLinesCustomers(customerRepository.findAll());

	}

}
