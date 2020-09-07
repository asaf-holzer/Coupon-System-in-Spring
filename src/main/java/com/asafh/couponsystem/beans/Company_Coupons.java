package com.asafh.couponsystem.beans;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company_Coupons {
	@Id
	private int company_id;
	@Id
	private int coupon_id;
}
