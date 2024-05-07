package com.example.bankmanagement.controller;

import com.example.bankmanagement.model.Transaction;
import com.example.bankmanagement.service.AccountService;
import com.example.bankmanagement.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestBody Transaction transaction) {
        transaction.setTransactionType("deposit");
        float newBalance = accountService.getAccountById(transaction.getAccountId())
                .map(account -> account.getBalance() + transaction.getAmount())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        accountService.updateBalance(transaction.getAccountId(), newBalance);
        return transactionService.saveTransaction(transaction);
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestBody Transaction transaction) {
        transaction.setTransactionType("withdraw");
        float newBalance = accountService.getAccountById(transaction.getAccountId())
                .map(account -> account.getBalance() - transaction.getAmount())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        if (newBalance < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        accountService.updateBalance(transaction.getAccountId(), newBalance);
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/{accountId}/history")
    public List<Transaction> getTransactionHistory(@PathVariable String accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
