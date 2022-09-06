package com.bank;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountEventStoreTest {

    @Test
    public void getCurrentState() {
        AccountEventStore store = new AccountEventStore();

        new ESProcess(store).process(new CreditCmd(10));
        new ESProcess(store).process(new DebitCmd(5));
        new ESProcess(store).process(new CreditCmd(15));
        System.out.println(store.getCurrentState().getBalance());

    }
}