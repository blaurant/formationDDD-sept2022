package DDD.framework;

import java.util.function.Consumer;

public interface Bus {

    void publish(DomainEvent event);

    void consume(String channel, Consumer<Object> consumer);

}