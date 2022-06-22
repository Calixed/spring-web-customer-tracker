package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.entity.Customer;
import spring.service.CustomerService;

// This is where data will be placed before rendering in view
@Controller
@RequestMapping("/customer")
public class CustomerController {

	// injecting customer DAO
	@Autowired
	private CustomerService customerService; // spring will scan for component that implements the CustomerDAO

	// rendering the list of customer in home
	@GetMapping("/list")
	public String listCustomers(Model tempModel) {

		// retrieve customers from the dao
		List<Customer> customerList = customerService.getCustomers();

		// add the customers to the model
		tempModel.addAttribute("customers", customerList);

		// ready to render the value to the jsp pages
		return "list-customers";
	}

	// handles creating customer
	@GetMapping("/addCustomerForm")
	public String addCustomerForm(Model tempModel) {

		// create model attribute for bind form data
		Customer theCustomer = new Customer();

		// adding the columns of customer to the model
		tempModel.addAttribute("customer", theCustomer);

		// return the form
		return "customer-form";
	}

	@PostMapping("/saveCustomer") // this maps by the form action="saveCustomer"
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		// save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		// return the form
		return "redirect:/customer/list";
	}

	// handles for updating customer
	@GetMapping("/updateCustomerForm")
	public String updateCustomerForm(@RequestParam("customerId") int theId, Model tempModel) {

		// get the customer from the service layer
		Customer tempCustomer = customerService.getCustomer(theId);

		// set customer as a model attribute to pre-populate the form
		tempModel.addAttribute("customer", tempCustomer);

		// return the form
		return "customer-form";
	}

	@GetMapping("/deleteCustomer")
	public String deleleCustomerForm(@RequestParam("customerId") int theId) {
		
		// use customer service call in the DAO
		customerService.deleteCustomer(theId);
		
		// return the form
		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam("customerName") String tempCustomerName, Model tempModel) {

		// retrieve customers from the dao
		List<Customer> customerList = customerService.searchCustomer(tempCustomerName);
		
		// add the retrieved data from the customer service
		tempModel.addAttribute("customers", customerList);

		// return the form
		return "list-customers";
	}
}
