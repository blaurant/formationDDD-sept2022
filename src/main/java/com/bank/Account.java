package com.bank;

import DDD.framework.Command;
import DDD.framework.Commands;
import DDD.framework.DomainEvent;
import DDD.framework.DomainEvents;

public class Account {

    private int balance;

    public Account(DomainEvents events) {
        apply(events);
    }

    public Account apply(DomainEvents events) {
        events.domainEvents.forEach(event -> apply(event));
        return this;
    }

    public Account apply(DomainEvent domainEvent) {
        if (domainEvent instanceof AccountCreditedEvent)
            return apply((AccountCreditedEvent) domainEvent);
        else
            return apply((AccountDebitedEvent) domainEvent);
    }

    private Account apply(AccountCreditedEvent accountCreditedEvent) {
        this.balance = balance + accountCreditedEvent.amount;
        return this;
    }

    private Account apply(AccountDebitedEvent accountDebitedEvent) {
        this.balance = balance - accountDebitedEvent.amount;
        return this;
    }

    public DomainEvent decide(Command command) {
        if (command instanceof CreditCmd)
            return decide((CreditCmd) command);
        else
            return decide((DebitCmd) command);
    }

    private DomainEvent decide(CreditCmd creditCmd) {
        return new AccountCreditedEvent(creditCmd.amount);
    }

    private DomainEvent decide(DebitCmd debitCmd) {
        int newBalance = balance - debitCmd.amount;
        if (newBalance < 0)
            throw new RuntimeException("balance can not negative");
        return new AccountDebitedEvent(debitCmd.amount);
    }

    public int getBalance() {
        return balance;
    }
}
