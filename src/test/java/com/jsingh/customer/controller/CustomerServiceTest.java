package com.jsingh.customer.controller;

import com.jsingh.customer.entity.Customer;
import com.jsingh.customer.exceptions.CustomerValidationExceptions;
import com.jsingh.customer.repository.CustomerRepository;
import com.jsingh.customer.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    private Map<Integer,Customer> customerTestData = new HashMap<Integer,Customer>();

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;


    @Before
    public void setup() {
        customerTestData.put(1, new Customer(1, "testName1", "testSurname1"));
        customerTestData.put(2, new Customer(2, "testName2", "testSurname2"));
        customerTestData.put(3, new Customer(3, "testName3", "testSurname3"));

    }

    @Test
    public void getAllCustomersTest(){

        when(customerRepository.getCustomers()).thenReturn(customerTestData.values().stream().collect(Collectors.toList()));

        List<Customer> customerList = customerService.getCustomers();
        assertTrue("The returned response should be OK",customerList != null);
        assertEquals("Expected number of entries is wrong",customerList.size(),3);
    }

    @Test
    public void addCustomerDataSuccessTest(){
        Customer customer = new Customer(4, "addNew", "addNewSurname");
        when(customerRepository.addCustomer(customer)).thenReturn(true);

        Boolean response = customerService.addCustomer(customer);
        assertTrue("The returned response should be OK",response);
    }

    @Test
    public void addCustomerDataFailureTest(){
        Customer customer = new Customer(4, "addNew", "addNewSurname");

        when(customerRepository.addCustomer(customer)).thenThrow(new CustomerValidationExceptions("Error Creating Customer"));

        try {
            Boolean response = customerService.addCustomer(customer);
        }catch ( Exception e) {
            assertTrue("Expected error when adding non-existent customer", e.getMessage().contains("Error Creating Customer"));
        }
    }

    @Test
    public void deleteCustomerDataSuccessTest(){

        when(customerRepository.removeCustomer(1)).thenReturn(true);

        Boolean response = customerService.removeCustomer(1);
        assertTrue("The returned response should be OK",response);
    }

    @Test
    public void deleteCustomerDataFailureTest(){

        when(customerRepository.removeCustomer(10)).thenThrow(new CustomerValidationExceptions("Error Deleting Customer"));

        try {
            Boolean response = customerService.removeCustomer(10);
        }catch ( Exception e) {
            assertTrue("Expected error when deleting non-existent customer", e.getMessage().contains("Error Deleting Customer"));
        }
    }

}
