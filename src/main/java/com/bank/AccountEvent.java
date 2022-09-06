package com.bank;

import DDD.framework.DomainEvent;

public class AccountEvent implements DomainEvent {

    public int amount;

    public AccountEvent(int amount) {
        this.amount = amount;
    }

    @Override
    public String getChannel() {
        return "ACCOUNT";
    }
}
