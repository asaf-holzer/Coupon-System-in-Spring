package com.asafh.couponsystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asafh.couponsystem.Service.CompanyService;
import com.asafh.couponsystem.Service.CustomerService;
import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.beans.Customer;
import com.asafh.couponsystem.exceptions.DeniedException;
import com.asafh.couponsystem.exceptions.LoginDeniedExption;
import com.asafh.couponsystem.exceptions.NotExistExeption;
import com.asafh.couponsystem.exceptions.TokenIsNotExistExeption;
import com.asafh.couponsystem.sequrity.TokenManager;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController extends clientController{
	
	@Autowired
	private TokenManager tokenManager;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password ) throws LoginDeniedExption{
		try {
			return new ResponseEntity<Boolean>(customerService.login(email, password),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	
	@PostMapping("purchase-coupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon , @RequestHeader(name = "Authorization") String token) throws DeniedException, NotExistExeption, TokenIsNotExistExeption {
		try {
			tokenManager.isTokenIsExist(token);
			customerService= (CustomerService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<Coupon>(customerService.purchaseCoupon(coupon), HttpStatus.CREATED);
		} catch (DeniedException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-customer-coupons")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			customerService= (CustomerService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<List<Coupon>>(customerService.getCustomerCoupons(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-customer-coupons-category/{category}")
	public ResponseEntity<?> getCustomerCoupons(@PathVariable Category category , @RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			customerService= (CustomerService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<List<Coupon>>(customerService.getCustomerCoupons(category),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-customer-coupons-max-price/{maxPrice}")
	public ResponseEntity<?> getCustomerCoupons(@PathVariable double maxPrice , @RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			customerService= (CustomerService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<List<Coupon>>(customerService.getCustomerCoupons(maxPrice),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-customer-details")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			customerService= (CustomerService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<Customer>(customerService.getCustomerDetails(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-all-coupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			customerService= (CustomerService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<List<Coupon>>(customerService.getAllCoupons(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}

}
