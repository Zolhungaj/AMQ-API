package tech.zolhungaj.amqapi.servercommands;

/**
 * An interface that mostly exists to limit the types of objects that can be sent
 * DO NOT IMPLEMENT YOUR OWN SUBCLASSES, IT CAN AND WILL CHANGE
 */
public interface Command
{
    String commandName();
}
