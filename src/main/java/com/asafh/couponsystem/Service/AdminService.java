package com.asafh.couponsystem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Service;

import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Company_Coupons;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.beans.Customer;
import com.asafh.couponsystem.exceptions.DeniedException;
import com.asafh.couponsystem.exceptions.LoginDeniedExption;
import com.asafh.couponsystem.exceptions.NotExistExeption;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService extends ClientService {

	public AdminService() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws LoginDeniedExption {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		throw new LoginDeniedExption();
		
	}

	public Company addCompany(Company company) throws DeniedException {
		List<Company> result = companyRepository.findAll();
		for (Company company2 : result) {
			if (company2.getName().equals(company.getName()) || company2.getEmail().equals(company.getEmail())) {
				throw new DeniedException("the compamy didnt added: the name or email is allredy exist");
			}
		}

		return companyRepository.save(company);
	}

	public Company updateCompany(int companyID, Company company) throws DeniedException  {
		if (companyRepository.findById(companyID).equals(Optional.empty())) {
			throw new DeniedException("you cant update : a company with this id is not exist");
		}
		Company comp = companyRepository.getOne(companyID);
		if (company.getId() != comp.getId() || !company.getName().equals(comp.getName())) {
			throw new DeniedException("you cant update id or name");
		}
		return companyRepository.saveAndFlush(company);
	}

	@Transactional
	public int deleteCompany(int id) throws NotExistExeption {
		if (companyRepository.findById(id).equals(Optional.empty())) {
			throw new NotExistExeption("the compamy didnt deleted: a company with this id is not exist");
		}
		List<Coupon> resultCoupons = couponRepository.findAll();
		for (Coupon coupon : resultCoupons) {
			if (coupon.getCompanyID() == id) {
				customerRepository.deleteFromCustomer_coupons(coupon.getId());
			}
		}

		companyRepository.deleteById(id);
		return id;
	}
	
	

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company getCompanyByID(int id) throws NotExistExeption  {
		if (companyRepository.findById(id).equals(Optional.empty())) {
			throw new NotExistExeption("a company with this id is not exist");
		}
		return companyRepository.getOne(id);
	}

	public Customer addCustomer(Customer customer) throws DeniedException  {
		List<Customer> result = customerRepository.findAll();
		for (Customer custmer2 : result) {
			if (custmer2.getEmail().equals(customer.getEmail())) {
				throw new DeniedException("the customer didnt added: the email is allredy exist");
			}
		}

		return customerRepository.save(customer);
	}

	public Customer updateCustomer(int customerID, Customer customer) throws DeniedException  {
		if (customerRepository.findById(customerID).equals(Optional.empty())) {
			throw new DeniedException("you cant update : a customer with this id is not exist");
		}
		Customer cust = customerRepository.getOne(customerID);
		if (customer.getId() != cust.getId()) {
			throw new DeniedException("you cant update id");
		}
		return customerRepository.saveAndFlush(customer);
	}

	public int deleteCustomer(int id) throws DeniedException {
		if (customerRepository.findById(id).equals(Optional.empty())) {
			throw new DeniedException("the customer didnt deleted: a customer with this id is not exist");
		}
		Customer temp=customerRepository.getOne(id);
		temp.setCoupons(new ArrayList<Coupon>());
		customerRepository.saveAndFlush(temp);
		customerRepository.deleteById(id);
		return id;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerByID(int id) throws NotExistExeption {
		if (customerRepository.findById(id).equals(Optional.empty())) {
			throw new NotExistExeption("a customer with this id is not exist");
		}
		return customerRepository.getOne(id);

	}
	
	@Transactional
	public boolean isCompanyExist(String email, String password) {
		if (companyRepository.existsByEmailAndPassword(email, password)) {
			return true;
		}
		return false;
	}


}
