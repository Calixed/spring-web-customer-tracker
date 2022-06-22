package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query; // Hibernate 5.2
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.entity.Customer;

@Repository // always applied in any DAO implementation classes
public class CustomerDAOImpl implements CustomerDAO {

	// required to inject the session Factory
	@Autowired
	private SessionFactory sessionFactory; // this is a bean in the spring-config as dataSoruce

	@Override
	public List<Customer> getCustomers() {

		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sorted by the last name
		Query<Customer> queryCommand = currentSession.createQuery("from Customer order by lastName", Customer.class);

		// execture and get result list
		List<Customer> customers = queryCommand.getResultList();

		// return the list
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// there two ways of saving; save new record or update existing record
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int tempCustomerId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Customer tempCustomer = currentSession.get(Customer.class, tempCustomerId);

		return tempCustomer;
	}

	@Override
	public void deleteCustomer(int tempCustomerId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query queryCommand = currentSession.createQuery("delete from Customer where id=:customerId");

		// setting the parameters of the query command for id=:customerId
		queryCommand.setParameter("customerId", tempCustomerId);

		queryCommand.executeUpdate();
	}
   
	@Override
	public List<Customer> searchCustomer(String tempCustomerName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create query null
		Query queryCommand = null;

		// creating query if the submit string is not empty or not
		if (tempCustomerName != null && tempCustomerName.trim().length() > 0) {

			// search for the firstName or lastName (case sensitive)
			queryCommand = currentSession.createQuery(
					"from Customer where lower(firstName) like :tempName or lower(lastName) like :tempName",
					Customer.class);

			// setting the parameters to :tempName
			queryCommand.setParameter("tempName", "%" + tempCustomerName.toLowerCase() + "%");

		} else {

			// the tempCustomerName is empty, get all customer
			queryCommand = currentSession.createQuery("from Customer", Customer.class);
		}

		// execute the query and get result list
		List<Customer> customers = queryCommand.getResultList();

		return customers;
	}

}
