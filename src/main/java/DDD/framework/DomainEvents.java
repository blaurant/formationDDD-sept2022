package DDD.framework;

import io.vavr.collection.List;

import java.util.function.Consumer;

public class DomainEvents {

    public static DomainEvents emptyEvents = new DomainEvents(List.empty());

    public final List<DomainEvent> domainEvents;

    public DomainEvents(List<DomainEvent> domainEvents) {
        this.domainEvents = domainEvents;
    }

    public DomainEvents append(DomainEvent domainEvent) {
        return new DomainEvents(domainEvents.append(domainEvent));
    }

}
