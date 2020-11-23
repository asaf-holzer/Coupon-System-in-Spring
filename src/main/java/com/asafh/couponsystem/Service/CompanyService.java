package com.asafh.couponsystem.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asafh.couponsystem.beans.Category;
import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.exceptions.DeniedException;
import com.asafh.couponsystem.exceptions.LoginDeniedExption;


@Service
@Scope("prototype")
public class CompanyService extends ClientService {

	private int companyID;

	public CompanyService() {
		super();
	}

	public int getCompanyID() {
		return companyID;
	}

	public int getCompanyID(String email, String password) {
		return companyRepository.findByEmailAndPassword(email, password).getId();
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	@Override
	public boolean login(String email, String password) throws LoginDeniedExption {
		if (!companyRepository.existsByEmailAndPassword(email, password)) {
			throw new LoginDeniedExption();
		}
		companyID=companyRepository.findByEmailAndPassword(email, password).getId();
		System.out.println("company logged in successfully" +this.companyID);
		return true;
	}

	public Coupon addCoupon(Coupon coupon) throws DeniedException {
		List<Coupon> resultCoupons = couponRepository.findAll();
		for (Coupon coupon2 : resultCoupons) {
			if (coupon2.getCompanyID() == coupon.getCompanyID() && coupon2.getTitle().equals(coupon.getTitle())) {
				throw new DeniedException("sorry... you can't add coupon with the same title");
			}
		}
		if (coupon.getCompanyID() != companyID) {
			throw new DeniedException("sorry... you can't add coupon of another company");
		}
		couponRepository.save(coupon);
		Company tempCompany = companyRepository.getOne(companyID);
		List<Coupon> resultCoupons2 = getCompanyCoupons();
		tempCompany.setCoupons(resultCoupons2);
		companyRepository.saveAndFlush(tempCompany);
		return coupon;

	}

	public Coupon updateCoupon(Integer couponID, Coupon coupon) throws DeniedException  {
		Coupon coup = couponRepository.getOne(couponID);
		if (coupon.getId() != couponID || coupon.getCompanyID() != couponRepository.getOne(couponID).getCompanyID()) {
			throw new DeniedException("sorry... you can't update the coupon_id or company");
		}
		if (coupon.getCategory() != null) {
			coup.setCategory(coupon.getCategory());
		}
		if (coupon.getTitle() != null) {
			coup.setTitle(coupon.getTitle());
		}
		if (coup.getDescription() != null) {
			coup.setDescription(coupon.getDescription());
		}
		if (coupon.getStartDate() != null) {
			coup.setStartDate(coupon.getStartDate());
		}
		if (coupon.getEndDate() != null) {
			coup.setEndDate(coupon.getEndDate());
		}
		if (coupon.getAmount() > 0) {
			coup.setAmount(coupon.getAmount());
		}
		if (coupon.getPrice() >= 0) {
			coup.setPrice(coupon.getPrice());
		}
		if (coupon.getImage() != null) {
			coup.setImage(coupon.getImage());
		}

		return couponRepository.saveAndFlush(coupon);
	}

	@Transactional
	public int deleteCoupon(int couponID) {
		customerRepository.deleteFromCustomer_coupons(couponID);
		couponRepository.deleteById(couponID);
		return couponID;
	}

	public List<Coupon> getCompanyCoupons() {
		List<Coupon> resultCoupons = couponRepository.findAll();
		List<Coupon> filteredCoupons = new ArrayList<Coupon>();
		for (Coupon coupon : resultCoupons) {
			if (coupon.getCompanyID() == companyID) {
				filteredCoupons.add(coupon);
			}
		}
		return filteredCoupons;

	}

	public List<Coupon> getCompanyCoupons(Category category) {
		List<Coupon> resultCoupons = getCompanyCoupons();
		List<Coupon> filteredList = new ArrayList<Coupon>();
		for (Coupon coupon : resultCoupons) {
			if (coupon.getCategory().equals(category)) {
				filteredList.add(coupon);
			}
		}
		return filteredList;
	}

	public List<Coupon> getCompanyCoupons(double maxPrice) {
		List<Coupon> resultCoupons = getCompanyCoupons();
		List<Coupon> filteredList = new ArrayList<Coupon>();
		for (Coupon coupon : resultCoupons) {
			if (coupon.getPrice() <= maxPrice) {
				filteredList.add(coupon);
			}
		}
		return filteredList;
	}

	public Company getcompanyDetails() {
		return companyRepository.getOne(companyID);
	}
	
	

}
