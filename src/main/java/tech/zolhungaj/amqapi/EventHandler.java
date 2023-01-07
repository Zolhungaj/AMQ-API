package tech.zolhungaj.amqapi;

import tech.zolhungaj.amqapi.servercommands.Command;

public interface EventHandler {
    boolean call(Command event);
}
