package tech.zolhungaj.amqapi.clientcommands;

/**
 * For use when a command does not have a data component
 */
public abstract class EmptyClientCommand extends AbstractClientCommand{
    protected EmptyClientCommand(ClientCommandType type){
        super(type);
    }
}
