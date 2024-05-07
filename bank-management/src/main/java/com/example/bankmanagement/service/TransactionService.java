package com.example.bankmanagement.service;

import com.example.bankmanagement.model.Transaction;
import com.example.bankmanagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findAllByAccountId(accountId);
    }
}
