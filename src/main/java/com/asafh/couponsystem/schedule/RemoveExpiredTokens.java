package com.asafh.couponsystem.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.sequrity.TokenManager;

@Component
public class RemoveExpiredTokens {

	@Autowired
	private TokenManager tokenManager;
	
	@Scheduled(fixedRate = 1000*5)
	public void removeTokens() {
		tokenManager.removeExpiredTokens();
	}
	
	
	
}
