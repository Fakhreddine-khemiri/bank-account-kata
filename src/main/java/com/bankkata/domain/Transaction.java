package com.bankkata.domain;

import java.time.LocalDate;
import java.util.Objects;

public record Transaction(LocalDate date, int amount, int balanceAfter) {
    public Transaction {
        Objects.requireNonNull(date, "Date must not be null");
    }
}