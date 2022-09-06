package com.bank;

import DDD.framework.Command;

public class DebitCmd implements Command {

    public int amount;

    public DebitCmd(int amount) {
        this.amount = amount;
    }
}
