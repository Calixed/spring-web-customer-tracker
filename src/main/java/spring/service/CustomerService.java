package spring.service;

import java.util.List;

import spring.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int tempCustomerId);

	public void deleteCustomer(int tempCustomerId);

	public List<Customer> searchCustomer(String tempCustomerName);
	
}
