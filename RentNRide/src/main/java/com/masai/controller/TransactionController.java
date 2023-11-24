package com.masai.controller;

import java.util.List;

import com.masai.model.Transaction;
import com.masai.service.TransactionService;

public class TransactionController {

    private TransactionService transactionService;

    public TransactionController() {
        this.transactionService = new TransactionService();
    }

    public void addTransaction(Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    public void updateTransaction(Transaction transaction) {
        transactionService.updateTransaction(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        transactionService.deleteTransaction(transaction);
    }
}