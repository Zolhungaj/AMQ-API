package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;

public record PlayerChangedAvatar(
        int gamePlayerId,
        PlayerAvatar avatar
)
implements Command {
    @Override
    public String commandName() {
        return CommandType.AVATAR_CHANGE.commandName;
    }
}
