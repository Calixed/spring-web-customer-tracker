package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query; // Hibernate 5.2
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.Customer;

@Repository // always applied in any DAO implementation classes
public class CustomerDAOImpl implements CustomerDAO {
	
	// required to inject the session Factory
	@Autowired
	private SessionFactory sessionFactory; // this is a bean in the spring-config as dataSoruce
	
	@Override
	@Transactional // automatic inject the session factory
	public List<Customer> getCustomers() {
		
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query 
		Query<Customer> queryCommand = currentSession.createQuery("from Customer", Customer.class);
		
		// execture and get result list
		List<Customer> customers = queryCommand.getResultList();
		
		// return the list
		return customers;
	}

}
