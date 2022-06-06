package tech.zolhungaj.amqapi;

import tech.zolhungaj.amqapi.commands.Command;

interface EventHandler<T extends Command > {
    boolean call(T event);
}
