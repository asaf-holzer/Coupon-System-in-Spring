package com.asafh.couponsystem.sequrity;

import com.asafh.couponsystem.Service.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

	private ClientService clientService;
	private long timeStemp;
	
	
}
