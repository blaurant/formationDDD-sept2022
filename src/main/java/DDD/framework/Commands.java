package DDD.framework;

import io.vavr.collection.List;

public class Commands {

    public static Commands emptyCommands = new Commands(List.empty());
    public final List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }
}
