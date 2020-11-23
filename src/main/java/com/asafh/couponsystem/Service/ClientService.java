package com.asafh.couponsystem.Service;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.asafh.couponsystem.exceptions.LoginDeniedExption;
import com.asafh.couponsystem.repo.CompanyRepository;
import com.asafh.couponsystem.repo.CouponRepository;
import com.asafh.couponsystem.repo.CustomerRepository;

import lombok.ToString;

@ToString
@Service
public abstract class ClientService {

	
	@Autowired
	protected CompanyRepository companyRepository;
	
	@Autowired
	protected CouponRepository couponRepository;
	
	@Autowired
	protected CustomerRepository customerRepository;
	
	public ClientService() {
		
	}
	
	public abstract boolean login(String email, String password) throws LoginDeniedExption ;
	
}
