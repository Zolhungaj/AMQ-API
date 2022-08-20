package tech.zolhungaj.amqapi;

import tech.zolhungaj.amqapi.servercommands.Command;

interface EventHandler {
    boolean call(Command event);
}
