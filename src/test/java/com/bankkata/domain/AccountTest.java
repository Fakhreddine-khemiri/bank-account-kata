package com.bankkata.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void givenNegativeAmount_whenDepositing_thenThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test
    void givenPositiveAmount_whenDepositing_thenIncreaseBalance() {

        account.deposit(200);

        assertEquals(200, account.getBalance());
    }

    @Test
    void givenDeposit_whenRecordingTransaction_thenTransactionIsStoredCorrectly() {

        account.deposit(200);
        List<Transaction> transactions = account.getTransactions();

        assertEquals(1, transactions.size());
        Transaction transaction = transactions.getFirst();
        assertEquals(200, transaction.amount());
        assertEquals(200, transaction.balanceAfter());
        assertEquals(LocalDate.now(), transaction.date());
    }

    @Test
    void givenNegativeAmount_whenWithdrawing_thenThrowIllegalArgumentException() {
        account.deposit(200);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50));
    }

    @Test
    void givenAmountGreaterThanBalance_whenWithdrawing_thenThrowIllegalStateException() {
        account.deposit(100);

        assertThrows(IllegalStateException.class, () -> account.withdraw(150));
    }

    @Test
    void givenSufficientBalance_whenWithdrawing_thenDecreaseBalance() {
        account.deposit(300);

        account.withdraw(100);

        assertEquals(200, account.getBalance());
    }

    @Test
    void givenWithdrawal_whenRecordingTransaction_thenTransactionIsStoredCorrectly() {
        account.deposit(500);
        account.withdraw(200);
        List<Transaction> transactions = account.getTransactions();

        assertEquals(2, transactions.size());
        Transaction withdrawal = transactions.get(1);
        assertEquals(-200, withdrawal.amount());
        assertEquals(300, withdrawal.balanceAfter());
        assertEquals(LocalDate.now(), withdrawal.date());
    }

    @Test
    void givenNoAccessToTransactionList_whenTryingToModify_thenThrowException() {
        account.deposit(100);

        List<Transaction> transactions = account.getTransactions();
        assertThrows(UnsupportedOperationException.class, () -> transactions.add(
                new Transaction(LocalDate.now(), 999, 9999)
        ));
    }
}
