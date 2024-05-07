package com.example.bankmanagement.repository;

import com.example.bankmanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findAllByAccountId(String accountId);
}
