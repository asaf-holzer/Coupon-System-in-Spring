package com.asafh.couponsystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asafh.couponsystem.Service.CompanyService;
import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.exceptions.DeniedException;
import com.asafh.couponsystem.exceptions.LoginDeniedExption;
import com.asafh.couponsystem.sequrity.TokenManager;

@RestController
@RequestMapping("company")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CompanyController extends clientController {
	
	@Autowired
	private TokenManager tokenManager;
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password) throws LoginDeniedExption{
		System.out.println("company log" );

		return new ResponseEntity<Boolean>(companyService.login(email, password),HttpStatus.OK);
	}
	
	@PostMapping("add-coupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon , @RequestHeader(name = "Authorization") String token) throws DeniedException{
		try {
			tokenManager.isTokenIsExist(token);
			companyService= (CompanyService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<Coupon>(companyService.addCoupon(coupon),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@PutMapping("update-coupon/{id}")
	public ResponseEntity<?> updateCoupon(@PathVariable int id,@RequestBody Coupon coupon , @RequestHeader(name = "Authorization") String token) throws DeniedException{
		try {
			tokenManager.isTokenIsExist(token);
			companyService= (CompanyService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<Coupon>(companyService.updateCoupon(id, coupon),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@DeleteMapping("delete-coupon/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable int id , @RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			companyService= (CompanyService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<Integer>(companyService.deleteCoupon(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-company-coupons")
	public ResponseEntity<?>  getCompanyCoupons( @RequestHeader(name = "Authorization") String token){
		
		try {
			tokenManager.isTokenIsExist(token);
			companyService= (CompanyService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-company-coupons-category/{category}")
	public ResponseEntity<?>  getCompanyCoupons(@PathVariable Category category , @RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			companyService= (CompanyService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(category),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-company-coupons-max-price/{maxPrice}")
	public ResponseEntity<?>  getCompanyCoupons(@PathVariable double maxPrice , @RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			companyService= (CompanyService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(maxPrice),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("get-company-details")
	public ResponseEntity<?> getCompanyDetails( @RequestHeader(name = "Authorization") String token){
		try {
			tokenManager.isTokenIsExist(token);
			companyService= (CompanyService) tokenManager.getMap().get(token).getClientService();
			return new ResponseEntity<Company>(companyService.getcompanyDetails(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	
	
	
	
	

}
