package com.asafh.couponsystem.schedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asafh.couponsystem.beans.Coupon;
import com.asafh.couponsystem.repo.CouponRepository;
import com.asafh.couponsystem.utils.BeautyTable;

@Component
public class CouponExpirationDailyJob {

	@Autowired
	private CouponRepository couponRepository;

	public Date realDate(int dd, int mm, int yyyy) {
		return new Date(yyyy - 1900, mm - 1, dd + 1);
	}

//	@Scheduled(initialDelay = 10 * 1000, fixedRate = 1000 * 60 *60*24)
//	@Transactional
	public void dailyTask() {

		System.out.println("update coupon #5 end date to be expired...");
		Coupon temp = couponRepository.getOne(5);
		temp.setEndDate(realDate(29, 8, 2020));
		couponRepository.saveAndFlush(temp);
		System.out.println("coupons before DailyJob working:");
		BeautyTable.tableWithLinesCoupons(couponRepository.findAll());
		List<Coupon> expiredCoupons = couponRepository.findAll();
		for (Coupon coupon : expiredCoupons) {
			if (coupon.getEndDate().before(new Date())) {
				couponRepository.deleteFromCompany_coupons(coupon.getId());
				couponRepository.deleteFromCustomer_coupons(coupon.getId());
				couponRepository.deleteById(coupon.getId());
			}
		}
		System.out.println("coupons after DailyJob working:");
		BeautyTable.tableWithLinesCoupons(couponRepository.findAll());

	}
}
