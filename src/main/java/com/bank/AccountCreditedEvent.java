package com.bank;

import DDD.framework.DomainEvent;

public class AccountCreditedEvent extends AccountEvent {

    public AccountCreditedEvent(int amount) {
        super(amount);
    }

}
