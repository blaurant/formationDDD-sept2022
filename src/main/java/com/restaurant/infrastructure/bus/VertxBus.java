package com.restaurant.infrastructure.bus;

import DDD.framework.Bus;
import DDD.framework.DomainEvent;
import com.restaurant.domain.*;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

import java.util.function.Consumer;

public class VertxBus implements Bus {

    private final EventBus eventBus;

    public static VertxBus createVertxBusWithGenCodec(Vertx vertx) {
        EventBus ebus = vertx.eventBus();
        ebus.registerDefaultCodec(MealPaidEvent.class, new GenericCodec<>(MealPaidEvent.class));
        ebus.registerDefaultCodec(TableOccupiedEvent.class, new GenericCodec<>(TableOccupiedEvent.class));
        ebus.registerDefaultCodec(TableFreedEvent.class, new GenericCodec<>(TableFreedEvent.class));
        ebus.registerDefaultCodec(TableSetEvent.class, new GenericCodec<>(TableSetEvent.class));
        return new VertxBus(ebus);
    }

    private VertxBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void publish(DomainEvent event) {
        eventBus.publish(event.getChannel(), event);
    }

    @Override
    public void consume(String channel, Consumer<Object> consumer) {
        eventBus.consumer(channel).handler(event -> consumer.accept(event.body()));
    }
}
