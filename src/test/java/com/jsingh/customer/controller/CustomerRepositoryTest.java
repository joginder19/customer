package com.jsingh.customer.controller;

import com.jsingh.customer.entity.Customer;
import com.jsingh.customer.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    private Map<Integer,Customer> customerTestData = new HashMap<Integer,Customer>();

    @InjectMocks
    private CustomerRepository customerRepository;

    @Before
    public void setup() {
        customerTestData.put(1, new Customer(1, "testName1", "testSurname1"));
        customerTestData.put(2, new Customer(2, "testName2", "testSurname2"));
        customerTestData.put(3, new Customer(3, "testName3", "testSurname3"));

    }

    @Test
    public void getAllCustomersTest(){

        List<Customer> customerList = customerRepository.getCustomers();
        assertTrue("The returned response should not be null ",customerList != null);
        assertEquals("Expected number of entries is wrong",customerList.size(),3);
    }

    @Test
    public void addCustomerDataSuccessTest(){
        Customer customer = new Customer(4, "addNew", "addNewSurname");

        Boolean response = customerRepository.addCustomer(customer);
        assertTrue("The returned response should be true",response);
    }

    @Test
    public void addCustomerDataFailureTest(){
        Customer customer = new Customer(4, "addNew", "addNewSurname");

        try {
            Boolean response = customerRepository.addCustomer(customer);
        }catch ( Exception e) {
            assertTrue("Expected error when adding existing customer", e.getMessage().contains("Customer already exists"));
        }
    }

    @Test
    public void deleteCustomerDataSuccessTest(){


        Boolean response = customerRepository.removeCustomer(1);
        assertTrue("The returned response should be OK",response);
    }

    @Test
    public void deleteCustomerDataFailureTest(){

        try {
            Boolean response = customerRepository.removeCustomer(10);
        }catch ( Exception e) {
            assertTrue("Expected error when deleting non-existent customer", e.getMessage().contains("Customer id does not exist"));
        }
    }

}
