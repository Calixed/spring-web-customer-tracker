package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.CustomerDAO;
import spring.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// injecting customer DAO
	@Autowired
	private CustomerDAO customerDAO; // spring will scan for componetns that implements the CustomerDAO
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		// retrieve customers from the dao
		List<Customer> customerList = customerDAO.getCustomers();
		
		// add the customers to the model
		theModel.addAttribute("customers",customerList);
		
		return "list-customers";
	}
}
