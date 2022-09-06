package com.bank;

import DDD.framework.Command;
import DDD.framework.Commands;
import DDD.framework.DomainEvent;
import DDD.framework.DomainEvents;

import static DDD.framework.DomainEvents.emptyEvents;

public class ESProcess {

    private DomainEvents domainEvents = emptyEvents;
    private final AccountEventStore accountEventStore;

    public ESProcess(AccountEventStore accountEventStore) {
        this.accountEventStore = accountEventStore;
    }

    public void process(Command command) {
        DomainEvent domainEvent = decide(command, accountEventStore.getCurrentState());
        Account accountNewState = evolve(domainEvent, accountEventStore.getCurrentState());
        publish(domainEvent);
    }

    private void publish(DomainEvent domainEvent) {
        accountEventStore.save(domainEvent);
    }

    /**
     * decide (cmd, state)  -> events
     */
    private DomainEvent decide(Command command, Account accountCurrentState) {
        return accountCurrentState.decide(command);
    }

    /**
     * evolve (Events, state) -> state
     */
    private Account evolve(DomainEvent domainEvent, Account accountCurrentState) {
        return accountCurrentState.apply(domainEvent);
    }

}
