package com.asafh.couponsystem.sequrity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.hibernate.engine.spi.EntityEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.exceptions.TokenIsNotExistExeption;


import lombok.Getter;

@Component
@Getter
public class TokenManager {

	@Autowired
	private Map<String, UserData> map;

	public void insertToken(String token, UserData userData) {
		map.put(token, userData);
	}

	public void deleteToken(String token) {
		map.remove(token);
	}

	public boolean isTokenIsExist(String token) throws TokenIsNotExistExeption {
		if (map.containsKey(token)) {
			return true;
		}
		throw new TokenIsNotExistExeption("Sorry... something went wrong...");
	}

	public void removeExpiredTokens() {

		System.out.println(Arrays.asList(map.keySet()));
		Iterator<Entry<String, UserData>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, UserData> entry =iterator.next();
			Date tokenTime30 = new Date(entry.getValue().getTimeStemp() + TimeUnit.MINUTES.toMillis(2));
			Date timeNow = new Date(System.currentTimeMillis());
			if (tokenTime30.before(timeNow)) {
				iterator.remove();
			}
		}
		System.out.println("deleting...");
		System.out.println(map.keySet());

	}

//	public void removeExpiredTokens() {
//		
//		System.out.println(Arrays.asList(map.keySet())); 
//		for(Map.Entry<String, UserData> entry : map.entrySet())	{
//			Date tokenTime30 =new Date(entry.getValue().getTimeStemp()+TimeUnit.MINUTES.toMillis(2));
//			Date timeNow = new Date(System.currentTimeMillis());
//			if (tokenTime30.before(timeNow)) {
//				map.remove(entry.getKey());
//	
//			}
//		}
//		System.out.println("deleting...");
//		System.out.println(Arrays.asList(map.keySet())); 
//			
//	}
}
