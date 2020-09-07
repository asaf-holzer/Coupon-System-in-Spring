package com.asafh.couponsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponsystemApplication.class, args);
		System.err.println("Ready...");
	}

}
