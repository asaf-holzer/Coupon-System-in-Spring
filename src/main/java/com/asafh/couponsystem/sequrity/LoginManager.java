package com.asafh.couponsystem.sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.asafh.couponsystem.Service.AdminService;
import com.asafh.couponsystem.Service.ClientService;
import com.asafh.couponsystem.Service.CompanyService;
import com.asafh.couponsystem.Service.CustomerService;
import com.asafh.couponsystem.exceptions.LoginDeniedExption;

@Service
@Scope("prototype")
public class LoginManager {
	
	
	private ClientService clientService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CustomerService customerService;

	public ClientService Login(String email, String password, ClientType clientType) throws LoginDeniedExption {
		switch (clientType) {
		case Administrator:
			clientService = adminService;
			if (clientService.login(email, password)) {
				return clientService;
			} else {
				throw new LoginDeniedExption();
			}
		case Company:
			
			clientService = companyService;
			if (clientService.login(email, password)) {
				int companyID = ((CompanyService) clientService).getCompanyID(email,password) ;
				((CompanyService) clientService).setCompanyID(companyID);
				return clientService;
			} else {
				throw new LoginDeniedExption();
			}
		case Customer:
			clientService = customerService;
			if (clientService.login(email, password)) {
				int customerID = ((CustomerService) clientService).getCustomerID(email,password);
				((CustomerService) clientService).setCustomerID(customerID);
				return clientService;
			} else {
				throw new LoginDeniedExption();
			}
		default:
			return null;

		}
	
	}
	
}
