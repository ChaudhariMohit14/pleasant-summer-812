package com.masai.dao;

import java.util.List;

import com.masai.model.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public class TransactionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addTransaction(Transaction transaction) {
        entityManager.persist(transaction);
    }

    public Transaction getTransactionById(Long transactionId) {
        return entityManager.find(Transaction.class, transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return entityManager.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
    }

    public void updateTransaction(Transaction transaction) {
        entityManager.merge(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        entityManager.remove(transaction);
    }
}
