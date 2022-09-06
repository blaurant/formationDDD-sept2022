package com.bank;

import DDD.framework.DomainEvent;

public class AccountDebitedEvent extends AccountEvent {

    public AccountDebitedEvent(int amount) {
        super(amount);
    }

}
