package com.asafh.couponsystem.sequrity;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.asafh.couponsystem.Service.AdminService;
import com.asafh.couponsystem.Service.CompanyService;
import com.asafh.couponsystem.Service.CustomerService;
import com.asafh.couponsystem.exceptions.LoginDeniedExption;

@Service
@Scope("prototype")
public class LoginManager {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TokenManager tokenManager;

	public String login(String email, String password, ClientType clientType) throws LoginDeniedExption {
		System.out.println(email+" "+password+" "+clientType);
		System.out.println(clientType instanceof ClientType);
		switch (clientType) {
		case Administrator:
		{
			
			if (adminService.login(email, password)) {
				System.out.println("adminservice true");
				long time = System.currentTimeMillis();
				String token = UUID.randomUUID().toString();
				System.out.println(token);
				tokenManager.insertToken(token, new UserData(adminService, time));
				return token;
			} else {
				throw new LoginDeniedExption();
			}
		}
		case Company:

			if (companyService.login(email, password)) {
				int companyID = companyService.getCompanyID(email, password);
				companyService.setCompanyID(companyID);
				long time = System.currentTimeMillis();
				String token = UUID.randomUUID().toString();
				System.out.println(token);
				tokenManager.insertToken(token, new UserData(companyService, time));
				return token;
			} else {
				throw new LoginDeniedExption();
			}
		case Customer:
			if (customerService.login(email, password)) {
				int customerID = customerService.getCustomerID(email, password);
				customerService.setCustomerID(customerID);
				long time = System.currentTimeMillis();
				String token = UUID.randomUUID().toString();
				System.out.println(token);
				tokenManager.insertToken(token, new UserData(customerService, time));
				return token;
			} else {
				throw new LoginDeniedExption();
			}
		default:
			return null;

		}
	}
	
	public String logout(String token){
		tokenManager.deleteToken(token);
		return token;
	}

}

//public ClientService login(String email, String password, ClientType clientType) throws LoginDeniedExption {
//switch (clientType) {
//case Administrator:
//	clientService = adminService;
//	if (clientService.login(email, password)) {
//		return clientService;
//	} else {
//		throw new LoginDeniedExption();
//	}
//case Company:
//	
//	clientService = companyService;
//	if (clientService.login(email, password)) {
//		int companyID = ((CompanyService) clientService).getCompanyID(email,password) ;
//		((CompanyService) clientService).setCompanyID(companyID);
//		return clientService;
//	} else {
//		throw new LoginDeniedExption();
//	}
//case Customer:
//	clientService = customerService;
//	if (clientService.login(email, password)) {
//		int customerID = ((CustomerService) clientService).getCustomerID(email,password);
//		((CustomerService) clientService).setCustomerID(customerID);
//		return clientService;
//	} else {
//		throw new LoginDeniedExption();
//	}
//default:
//	return null;
//
//}
//
//
//
//
