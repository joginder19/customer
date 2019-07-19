package com.jsingh.customer.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jsingh.customer.entity.Customer;
import com.jsingh.customer.exceptions.CustomerValidationExceptions;

@Repository
public class CustomerRepository {

	private Map<Integer, Customer> customerData = new HashMap<Integer, Customer>();

	public CustomerRepository() {
		customerData.put(1, new Customer(1, "John", "Smith"));
		customerData.put(2, new Customer(2, "Alan", "Johnson"));
		customerData.put(3, new Customer(3, "Jessica", "Gold"));
	}

	public List<Customer> getCustomers() {
		return (List<Customer>) customerData.values().stream().collect(Collectors.toList());
	}

	public boolean addCustomer(Customer customer) throws CustomerValidationExceptions {

		if (customerData.containsKey(customer.getId()))
			throw new CustomerValidationExceptions("Customer already exists");
		else
			customerData.put(customer.getId(), customer);
		return true;
	}

	public boolean removeCustomer(int customerId) throws CustomerValidationExceptions {

		if (customerData.containsKey(customerId))
			customerData.remove(customerId);

		else
			throw new CustomerValidationExceptions("Customer id does not exist");
		return true;
		
	}
}
