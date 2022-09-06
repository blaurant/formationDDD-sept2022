package com.bank;

import DDD.framework.DomainEvent;
import DDD.framework.DomainEvents;

public class AccountEventStore {

    DomainEvents events = DomainEvents.emptyEvents;

    public Account getCurrentState() {
        return new Account(events);
    }

    public void save(DomainEvent domainEvent) {
        this.events = events.append(domainEvent);
    }
}


//
//        events.domainEvents.reduce((event1, event2) -> {
//            int balance = amount(event1) + amount(event2);
//            if (balance >= 0)
//                return new AccountCreditedEvent(balance);
//            else
//                return new AccountDebitedEvent(balance);
//        });
//        events.forEach(event -> apply(event));
//
//        return new Account()

//
//    private int amount(DomainEvent event1) {
//        if (event1 instanceof AccountCreditedEvent)
//            return ((AccountCreditedEvent) event1).amount;
//        else
//            return -((AccountCreditedEvent) event1).amount;
//    }