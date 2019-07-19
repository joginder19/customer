package com.jsingh.customer.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jsingh.customer.entity.Customer;
import com.jsingh.customer.exceptions.CustomerValidationExceptions;
import com.jsingh.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CustomerControllerTest {
	private Map<Integer,Customer> customerTestData = new HashMap<Integer,Customer>();
	
	@InjectMocks
	private CustomerController customerController;
	
	@Mock
	private CustomerService  customerService;
	
	
	@Before
	public void setup() {
		customerTestData.put(1, new Customer(1, "testName1", "testSurname1"));
		customerTestData.put(2, new Customer(2, "testName2", "testSurname2"));
		customerTestData.put(3, new Customer(3, "testName3", "testSurname3"));

	}
	
	@Test
	public void getAllCustomersTest(){
		
		when(customerService.getCustomers()).thenReturn(customerTestData.values().stream().collect(Collectors.toList()));
		
		ResponseEntity<List<Customer>> returnedResponse = customerController.getCustomers();
		assertEquals("The returned response should be OK",returnedResponse.getStatusCode(),HttpStatus.OK);
		assertEquals("Expected number of entries is wrong",returnedResponse.getBody().size(),3);
	}

	@Test
	public void addCustomerDataSuccessTest(){
		Customer customer = new Customer(4, "addNew", "addNewSurname");
		when(customerService.addCustomer(customer)).thenReturn(true);
		
		ResponseEntity<String> returnedResponse = customerController.addCustomer(customer);
		assertEquals("The returned response should be OK",returnedResponse.getStatusCode(),HttpStatus.OK);
	}

	@Test
	public void addCustomerDataFailureTest(){
		Customer customer = new Customer(4, "addNew", "addNewSurname");
		
		when(customerService.addCustomer(customer)).thenThrow(new CustomerValidationExceptions("Error Creating Customer"));
		
		ResponseEntity<String> returnedResponse = customerController.addCustomer(customer);
		assertEquals("The returned response should be OK",returnedResponse.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
		assertTrue("Expected error when deleting non-existent customer",returnedResponse.getBody().contains("Error Creating Customer"));
	}

	@Test
	public void deleteCustomerDataSuccessTest(){
		
		when(customerService.removeCustomer(1)).thenReturn(true);
		
		ResponseEntity<String> returnedResponse = customerController.removeCustomer(1);
		assertEquals("The returned response should be OK",returnedResponse.getStatusCode(),HttpStatus.OK);
	}

	@Test
	public void deleteCustomerDataFailureTest(){
		
		when(customerService.removeCustomer(10)).thenThrow(new CustomerValidationExceptions("Error Deleting Customer"));
		
		ResponseEntity<String> returnedResponse = customerController.removeCustomer(10);
		assertEquals("The returned response should be OK",returnedResponse.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
		assertTrue("Expected error when deleting non-existent customer",returnedResponse.getBody().contains("Error Deleting Customer"));
	}

}
