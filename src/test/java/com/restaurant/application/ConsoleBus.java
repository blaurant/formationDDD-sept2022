package com.restaurant.application;

import DDD.framework.Bus;
import DDD.framework.DomainEvent;

import java.util.function.Consumer;

public class ConsoleBus implements Bus {

    @Override
    public void publish(DomainEvent event) {
        System.out.println(event.getChannel() + " : publish : " + event);
    }

    @Override
    public void consume(String channel, Consumer<Object> consumer) {
    }
}