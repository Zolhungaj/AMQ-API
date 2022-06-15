package tech.zolhungaj.amqapi;

import tech.zolhungaj.amqapi.commands.Command;

interface EventHandler {
    boolean call(Command event);
}
