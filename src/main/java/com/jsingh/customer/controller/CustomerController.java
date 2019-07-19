package com.jsingh.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsingh.customer.entity.Customer;
import com.jsingh.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	/** getCustomer endpoint returns all the customer entries */
	@GetMapping("getCustomers")
	public ResponseEntity<List<Customer>> getCustomers() {
		return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
	}
	
	/** addCustomer endpoint add a customer record if the supplied customer id does not exists*/
	@PutMapping
	public ResponseEntity<String> addCustomer( @RequestBody Customer customer) {
		try {
			 customerService.addCustomer(customer);
			 return new ResponseEntity<>("Add customer success",HttpStatus.OK);
		} catch (Exception e) {
          return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/** removeCustomer endpoint delete a customer record if it exists for the give id */
	@DeleteMapping("removeCustomer")
	public ResponseEntity<String> removeCustomer(@RequestParam(required = true) int id) {
		try {
			 customerService.removeCustomer(id);
			 return new ResponseEntity<>("Delete customer success",HttpStatus.OK);
		} catch (Exception e) {
         return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
