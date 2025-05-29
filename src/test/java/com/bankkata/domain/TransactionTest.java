package com.bankkata.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void givenValidArguments_whenCreatingTransaction_thenTransactionIsCreatedCorrectly() {
        LocalDate date = LocalDate.of(2025, 5, 28);
        int amount = 100;
        int balanceAfter = 500;

        Transaction transaction = new Transaction(date, amount, balanceAfter);

        assertEquals(date, transaction.date());
        assertEquals(amount, transaction.amount());
        assertEquals(balanceAfter, transaction.balanceAfter());
    }

    @Test
    void givenNullDate_whenCreatingTransaction_thenThrowsException() {
        assertThrows(NullPointerException.class, () -> new Transaction(null, 100, 500));
    }
}
