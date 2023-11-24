package com.masai.service;

import java.util.List;

import com.masai.dao.TransactionDAO;
import com.masai.model.Transaction;

public class TransactionService {

    private TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
    }

    public void addTransaction(Transaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionDAO.getTransactionById(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    public void updateTransaction(Transaction transaction) {
        transactionDAO.updateTransaction(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        transactionDAO.deleteTransaction(transaction);
    }
}