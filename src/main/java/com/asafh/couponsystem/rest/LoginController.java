package com.asafh.couponsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asafh.couponsystem.exceptions.LoginDeniedExption;
import com.asafh.couponsystem.sequrity.ClientType;
import com.asafh.couponsystem.sequrity.LoginManager;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;


@RestController
@RequestMapping("loginManager")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LoginController {
	
	@Autowired 
	private LoginManager loginManager;

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestParam String email,@RequestParam String password, @RequestParam ClientType clientType) throws LoginDeniedExption{
		try {
			return new ResponseEntity<String>("\""+loginManager.login(email, password, clientType)+"\"" ,HttpStatus.OK);
		} catch (LoginDeniedExption e) {
			return new ResponseEntity<String>(e.getMessage() ,HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@DeleteMapping("logout")
	public ResponseEntity<?> logout(@RequestParam String token){
		System.err.println("logout!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		try {
			return new ResponseEntity<String>("\""+loginManager.logout(token)+"\"", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage() ,HttpStatus.UNAUTHORIZED);
		}
		
	}
	
}
