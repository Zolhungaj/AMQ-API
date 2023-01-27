package tech.zolhungaj.amqapi.clientcommands.lobby;

import lombok.Getter;
import lombok.ToString;
import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;

@Getter
@ToString

public final class Kick extends AbstractClientCommand {
    private final String playerName;
    public Kick(String playerName){
        super(ClientCommandType.KICK_PLAYER);
        this.playerName = playerName;
    }
}
