package com.asafh.couponsystem.clrS;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.asafh.couponsystem.Service.AdminService;
import com.asafh.couponsystem.beans.Company;
import com.asafh.couponsystem.beans.Customer;
import com.asafh.couponsystem.exceptions.NotExistExeption;
import com.asafh.couponsystem.utils.BeautyTable;
import com.asafh.couponsystem.utils.PrintLines;
import com.asafh.couponsystem.utils.PrintTitles;

@Component
@Order(1)
public class TestAdminService implements CommandLineRunner {

	public Date realDate(int dd, int mm, int yyyy) {
		return new Date(yyyy - 1900, mm - 1, dd + 1);
	}

	@Autowired
	private AdminService adminService;

	@Override
	public void run(String... args) throws Exception {
		
		PrintTitles.adminService();

		Company comp1 = new Company();
		comp1.setName("Osem");
		comp1.setEmail("osem@gmail.com");
		comp1.setPassword("123458");

		Company comp2 = new Company();
		comp2.setName("Coca-cola");
		comp2.setEmail("coca@cola.com");
		comp2.setPassword("12345");

		Company comp3 = new Company();
		comp3.setName("Pepsi");
		comp3.setEmail("pepsi@cola.com");
		comp3.setPassword("12346");

		Company comp4 = new Company();
		comp4.setName("Alfredo");
		comp4.setEmail("alf@redo.com");
		comp4.setPassword("12347");

		Company comp5 = new Company();
		comp5.setName("Coca-cola");
		comp5.setEmail("cocacola@gmail.com");
		comp5.setPassword("12348");

		Company comp6 = new Company();
		comp6.setName("CocacolaCompany");
		comp6.setEmail("coca@cola.com");
		comp6.setPassword("12349");

		Company comp7 = new Company();
		comp7.setName("El-Al");
		comp7.setEmail("el@al.com");
		comp7.setPassword("12350");

		Company comp8 = new Company();
		comp8.setName("Sony");
		comp8.setEmail("sony@gmail.com");
		comp8.setPassword("12351");

		Company comp9 = new Company();
		comp9.setName("Isrotel");
		comp9.setEmail("isro@tel.com");
		comp9.setPassword("12352");


		adminService.addCompany(comp1);
		adminService.addCompany(comp2);
		adminService.addCompany(comp3);
		adminService.addCompany(comp4);
		adminService.addCompany(comp7);
		adminService.addCompany(comp8);
		adminService.addCompany(comp9);

		// try add company with name or email exist
		PrintLines.printBoldLine();
		System.out.println("try add company with name exist");
		PrintLines.printOneLine();
		try {
			adminService.addCompany(comp5);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		PrintLines.printBoldLine();
		System.out.println("try add company with email exist");
		PrintLines.printOneLine();
		try {
			adminService.addCompany(comp6);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		PrintLines.printBoldLine();

		Thread.sleep(0);
		// the companies added by admin
		System.out.println("the companies added by admin");
		BeautyTable.tableWithLinesCompanies(adminService.getAllCompanies());

		PrintLines.printBoldLine();

		// try update companies
		System.out.println("try update companies");
		PrintLines.printOneLine();
		// try to update name company
		System.out.println("try to update name company");
		PrintLines.printOneLine();
		comp1.setName("Osem-Group");
		try {
			adminService.updateCompany(1, comp1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		Thread.sleep(500);
		System.out.println("try to update id company");
		PrintLines.printOneLine();
		comp1.setName("Osem");
		comp1.setId(18);
		try {
			adminService.updateCompany(1, comp1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// try update the other details
		PrintLines.printBoldLine();
		System.out.println("try update the ather details");
		PrintLines.printOneLine();
		comp1.setId(1);
		comp1.setEmail("osemgroup@gmail.com");
		comp1.setPassword("98765");
		adminService.updateCompany(1, comp1);

		// the companies after update
		System.out.println(" the companies after update");
		BeautyTable.tableWithLinesCompanies(adminService.getAllCompanies());

//		#################################################################################
//		// try delete company: look after customer buy a coupon
//		#################################################################################

		PrintLines.printBoldLine();
		System.out.println("try delete company: look after customer buy a coupon");
		PrintLines.printBoldLine();

		// try delete company dose not exist
		PrintLines.printBoldLine();
		System.out.println("try delete company dose not exist");
		PrintLines.printOneLine();
		try {
			adminService.deleteCompany(8);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		PrintLines.printBoldLine();

		PrintLines.printBoldLine();
		System.out.println("get company by id: #2");
		BeautyTable.tableWithLinesOneCompany(adminService.getCompanyByID(2));
		PrintLines.printOneLine();
		
		// try to get company not exist
		System.out.println("try to get company not exist");
		
		try {
			System.out.println(adminService.getCompanyByID(8));
		} catch (NotExistExeption e) {
			System.err.println(e.getMessage());
		}
		Customer cust1 = new Customer();
		cust1.setFirstName("Moshe");
		cust1.setLastName("Cohem");
		cust1.setEmail("Moshe@Cohen.com");
		cust1.setPassword("2345");

		Customer cust2 = new Customer();
		cust2.setFirstName("David");
		cust2.setLastName("Hamelech");
		cust2.setEmail("David@Hamelech.com");
		cust2.setPassword("2346");

		Customer cust3 = new Customer();
		cust3.setFirstName("Haim");
		cust3.setLastName("Kats");
		cust3.setEmail("Haim@Kats.com");
		cust3.setPassword("2347");

		Customer cust4 = new Customer();
		cust4.setFirstName("Dany");
		cust4.setLastName("Din");
		cust4.setEmail("Dany@Din.com");
		cust4.setPassword("2348");

		Customer cust5 = new Customer();
		cust5.setFirstName("Miryam");
		cust5.setLastName("Levi");
		cust5.setEmail("Miryam@Levi.com");
		cust5.setPassword("2349");

		Customer cust6 = new Customer();
		cust6.setFirstName("Israel");
		cust6.setLastName("Ross");
		cust6.setEmail("Miryam@Levi.com");
		cust6.setPassword("2350");

		Customer cust7 = new Customer();
		cust7.setFirstName("Ram");
		cust7.setLastName("Shefa");
		cust7.setEmail("ram@shefa.com");
		cust7.setPassword("2351");

		Customer cust8 = new Customer();
		cust8.setFirstName("Dina");
		cust8.setLastName("Israely");
		cust8.setEmail("dina@israely.com");
		cust8.setPassword("2352");

		Customer cust9 = new Customer();
		cust9.setFirstName("Eli");
		cust9.setLastName("Yahu");
		cust9.setEmail("Eli@Yahu.com");
		cust9.setPassword("2353");

	
		adminService.addCustomer(cust1);
		adminService.addCustomer(cust2);
		adminService.addCustomer(cust3);
		adminService.addCustomer(cust4);
		adminService.addCustomer(cust5);
		adminService.addCustomer(cust7);
		adminService.addCustomer(cust8);
		adminService.addCustomer(cust9);

		System.out.println("the customer added by admin");
		BeautyTable.tableWithLinesCustomers(adminService.getAllCustomers());

		System.out.println("try add customer with the same email");
		try {
			adminService.addCustomer(cust6);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		Thread.sleep(0);
		//try get all customers method
		PrintLines.printBoldLine();
		System.out.println("try get all customers method");
		BeautyTable.tableWithLinesCustomers(adminService.getAllCustomers());
		PrintLines.printBoldLine();
		
		
		//try get customer by id
		System.out.println("try get customer #2");
		BeautyTable.tableWithLinesOneCustomer(adminService.getCustomerByID(2));
		PrintLines.printBoldLine();
		
	}

}
