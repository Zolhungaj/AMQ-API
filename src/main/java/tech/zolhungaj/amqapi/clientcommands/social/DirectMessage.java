package tech.zolhungaj.amqapi.clientcommands.social;

import lombok.Getter;
import lombok.ToString;
import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;

@Getter
@ToString

public final class DirectMessage extends AbstractClientCommand {
    private final String target;
    private final String message;
    public DirectMessage(String target, String message){
        super(ClientCommandType.KICK_PLAYER);
        this.target = target;
        this.message = message;
    }
}
