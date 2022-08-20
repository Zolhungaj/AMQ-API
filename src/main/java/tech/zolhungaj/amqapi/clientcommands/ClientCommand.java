package tech.zolhungaj.amqapi.clientcommands;

public sealed interface ClientCommand permits AbstractClientCommand{
    String type();
    String command();
}
