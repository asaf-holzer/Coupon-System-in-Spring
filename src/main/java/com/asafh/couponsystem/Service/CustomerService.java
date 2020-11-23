package com.asafh.couponsystem.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.beans.Customer;
import com.asafh.couponsystem.exceptions.DeniedException;
import com.asafh.couponsystem.exceptions.LoginDeniedExption;
import com.asafh.couponsystem.exceptions.NotExistExeption;
import com.asafh.couponsystem.repo.CouponRepository;


@Service
@Scope("prototype")
public class CustomerService extends ClientService {

	@Autowired
	private CouponRepository couponRepository;

	private int customerID;

	public CustomerService() {
		super();
	}

	public int getCompanyID() {
		return customerID;
	}

	public int getCustomerID(String email, String password) {
		return customerRepository.findByEmailAndPassword(email, password).getId();
	}

	public void setCustomerID(int companyID) {
		this.customerID = companyID;
	}

//	@Override
//	public boolean login(String email, String password) {
//		return customerRepository.existsByEmailAndPassword(email, password);
//	}


	@Override
	public boolean login(String email, String password) throws LoginDeniedExption {
		if (!customerRepository.existsByEmailAndPassword(email, password)) {
			throw new LoginDeniedExption();
		}
		customerID=customerRepository.findByEmailAndPassword(email, password).getId();
			return true;
	}

	
	
	
	public Coupon purchaseCoupon(Coupon coupon) throws DeniedException {

		if (getCustomerCoupons().contains(coupon)) {
			throw new DeniedException("you bought this coupon already . you cant purchase this coupon again");
		}
		if (coupon.getAmount() <= 0) {
			throw new DeniedException("you cant buy this coupon. it out of stock");
		}
		if (coupon.getEndDate().before(new Date())) {
			throw new DeniedException("the coupon expired");
		}
		coupon.setAmount(coupon.getAmount() - 1);
		couponRepository.saveAndFlush(coupon);
		return addPurchase(coupon);
	}

	public List<Coupon> getCustomerCoupons() {

		return customerRepository.getOne(customerID).getCoupons();

	}

	public List<Coupon> getCustomerCoupons(Category category) {
		List<Coupon> resultCoupons = getCustomerCoupons();
		List<Coupon> filteredList = new ArrayList<Coupon>();
		for (Coupon coupon : resultCoupons) {
			if (coupon.getCategory().equals(category)) {
				filteredList.add(coupon);
			}
		}
		return filteredList;
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> resultCoupons = getCustomerCoupons();
		List<Coupon> filteredList = new ArrayList<Coupon>();
		for (Coupon coupon : resultCoupons) {
			if (coupon.getPrice() <= maxPrice) {
				filteredList.add(coupon);
			}
		}
		return filteredList;
	}

	public Customer getCustomerDetails() {
		return customerRepository.getOne(customerID);
	}

	
	//my addition
	private Coupon addPurchase(Coupon coupon) {
		Customer temp = customerRepository.getOne(customerID);
		List<Coupon> resultCoupons2 = getCustomerCoupons();
		resultCoupons2.add(coupon);
		temp.setCoupons(resultCoupons2);
		customerRepository.saveAndFlush(temp);
		return coupon;

	}
	
	//my addition for phase 3 
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}
}
