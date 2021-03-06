package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.CustomerDAO;
import spring.entity.Customer;

@Service // define in the service layer
public class CustomerServiceImpl implements CustomerService {

	// inject  customer dao
	@Autowired // component scanning to auto inject
	private CustomerDAO customerDAO;
		
	@Override
	@Transactional // inject the session factory
	public List<Customer> getCustomers() {	
		return customerDAO.getCustomers(); // delegate calls to DAO
	}

	@Override
	@Transactional 
	public void saveCustomer(Customer theCustomer) {
		// responsible for calling the DAO to save the customer
		customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int tempCustomerId) {
		// use DAO to retrieve the Customer data
		return customerDAO.getCustomer(tempCustomerId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int tempCustomerId) {
		// passing to the DAO customer id to delete it
		customerDAO.deleteCustomer(tempCustomerId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomer(String tempCustomerName) {
		
		return customerDAO.searchCustomer(tempCustomerName);
	}

}
