package tech.zolhungaj.amqapi.clientcommands;

public abstract class EmptyClientCommand extends AbstractClientCommand{
    protected EmptyClientCommand(ClientCommandType type){
        super(type);
    }
}
