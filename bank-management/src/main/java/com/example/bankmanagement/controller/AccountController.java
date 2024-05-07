package com.example.bankmanagement.controller;

import com.example.bankmanagement.model.Account;
import com.example.bankmanagement.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{accountId}")
    public Optional<Account> getAccountById(@PathVariable String accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("/{accountId}/balance")
    public float getBalance(@PathVariable String accountId) {
        return accountService.getAccountById(accountId)
                .map(Account::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
    }
}
