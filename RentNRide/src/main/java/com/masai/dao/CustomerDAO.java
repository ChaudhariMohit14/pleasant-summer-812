package com.masai.dao;

import java.util.List;

import com.masai.model.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer getCustomerById(Long customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public void updateCustomer(Customer customer) {
        entityManager.merge(customer);
    }

    public void deleteCustomer(Customer customer) {
        entityManager.remove(customer);
    }
}
