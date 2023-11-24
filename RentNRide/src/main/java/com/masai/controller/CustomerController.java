package com.masai.controller;

import java.util.List;

import com.masai.model.Customer;
import com.masai.service.CustomerService;

public class CustomerController {

    private CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService();
    }

    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    public Customer getCustomerById(Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerService.deleteCustomer(customer);
    }
}
