package com.jsingh.customer.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsingh.customer.entity.Customer;
import com.jsingh.customer.exceptions.CustomerValidationExceptions;
import com.jsingh.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> getCustomers(){
		return (List<Customer>) customerRepository.getCustomers();
	}
	
	
	public boolean addCustomer(Customer customer) throws CustomerValidationExceptions {
		 return customerRepository.addCustomer(customer);
	}
	
	
	public boolean removeCustomer(int id) throws CustomerValidationExceptions {
		 return customerRepository.removeCustomer(id);
	}
	
}
