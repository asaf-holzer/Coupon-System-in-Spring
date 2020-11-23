package com.asafh.couponsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.asafh.couponsystem.Service.AdminService;
import com.asafh.couponsystem.Service.CompanyService;
import com.asafh.couponsystem.Service.CustomerService;


public abstract class clientController {

	@Autowired
	protected AdminService adminService;
	
	@Autowired
	protected CompanyService companyService;
	
	@Autowired
	protected CustomerService customerService;
	
	
}
