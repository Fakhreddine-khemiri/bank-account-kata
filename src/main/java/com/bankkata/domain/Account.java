package com.bankkata.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public void deposit(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        recordTransaction(amount);
    }

    public void withdraw(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) throw new IllegalStateException("Insufficient balance");
        balance -= amount;
        recordTransaction(-amount);
    }

    public int getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    private void recordTransaction(int amount) {
        transactions.add(new Transaction(LocalDate.now(), amount, balance));
    }
}
