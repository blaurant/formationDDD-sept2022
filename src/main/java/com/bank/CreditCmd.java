package com.bank;

import DDD.framework.Command;

public class CreditCmd implements Command {

    public int amount;

    public CreditCmd(int amount) {
        this.amount = amount;
    }
}
