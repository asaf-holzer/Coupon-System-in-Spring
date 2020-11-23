package com.asafh.couponsystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Customer;
import com.asafh.couponsystem.exceptions.DeniedException;
import com.asafh.couponsystem.exceptions.LoginDeniedExption;
import com.asafh.couponsystem.exceptions.NotExistExeption;
import com.asafh.couponsystem.exceptions.TokenIsNotExistExeption;
import com.asafh.couponsystem.sequrity.TokenManager;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminController extends clientController {

	@Autowired
	private TokenManager tokenManager;
//	@PostMapping("login")
//	public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password) {
//		System.out.println("admin log" );
//		try {
//			return new ResponseEntity<Boolean>(adminService.login(email, password),HttpStatus.OK);
//		} catch (LoginDeniedExption e) {
//			System.out.println(e.getMessage());;
//		}
//		return null;
//	}

	@PostMapping("add-company")
	public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader(name = "Authorization") String token) {
		try {
			tokenManager.isTokenIsExist(token);
			return new ResponseEntity<Company>(adminService.addCompany(company), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("update-company/{id}")
	public ResponseEntity<?> updateCompany(@PathVariable int id, @RequestBody Company company,
			@RequestHeader(name = "Authorization") String token) throws DeniedException {
		try {
			tokenManager.isTokenIsExist(token);
			return new ResponseEntity<Company>(adminService.updateCompany(id, company), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("get-all-companies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) {
		try {
			tokenManager.isTokenIsExist(token);
			return new ResponseEntity<List<Company>>(adminService.getAllCompanies(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@DeleteMapping("delete-company/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable int id, @RequestHeader(name = "Authorization") String token) throws NotExistExeption {
		try {
			tokenManager.isTokenIsExist(token);
			return new ResponseEntity<Integer>(adminService.deleteCompany(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("get-one-company/{id}")
	public ResponseEntity<?> getCompanyByID(@PathVariable int id, @RequestHeader(name = "Authorization") String token) throws NotExistExeption {
		try {
			tokenManager.isTokenIsExist(token);
			adminService.getCompanyByID(id);
			return new ResponseEntity<Company>(adminService.getCompanyByID(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@PostMapping("add-customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader(name = "Authorization") String token)
			throws DeniedException {
		try {
			tokenManager.isTokenIsExist(token);
			return new ResponseEntity<Customer>(adminService.addCustomer(customer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@PutMapping("update-customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer customer,
			@RequestHeader(name = "Authorization") String token) throws DeniedException {
		try {
			tokenManager.isTokenIsExist(token);
			return new ResponseEntity<Customer>(adminService.updateCustomer(id, customer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@DeleteMapping("delete-customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id, @RequestHeader(name = "Authorization") String token) throws DeniedException {
		try {
			tokenManager.isTokenIsExist(token);
			return new ResponseEntity<Integer>(adminService.deleteCustomer(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("get-all-customers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) {
		try {
			tokenManager.isTokenIsExist(token);
			return new ResponseEntity<List<Customer>>(adminService.getAllCustomers(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("get-one-customer/{id}")
	public ResponseEntity<?> getCustomerByID(@PathVariable int id, @RequestHeader(name = "Authorization") String token) throws NotExistExeption {
		try {
			tokenManager.isTokenIsExist(token);
			adminService.getCustomerByID(id);
			return new ResponseEntity<Customer>(adminService.getCustomerByID(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

}
