//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.deadlock;

public class Account {
    private int balance = 10000;

    public void deposit(final int amount) {
        balance += amount;
    }

    public void withdraw(final int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(final Account acc1, final Account acc2,
            final int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}
