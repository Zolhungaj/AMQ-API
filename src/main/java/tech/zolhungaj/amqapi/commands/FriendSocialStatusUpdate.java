package tech.zolhungaj.amqapi.commands;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.commands.objects.PlayerGameState;
import tech.zolhungaj.amqapi.commands.objects.PlayerStatus;

import java.util.Optional;

public record FriendSocialStatusUpdate(
        String name,
        @Json(name = "socialStatus") Integer statusId,
        Optional<PlayerGameState> gameState
) implements Command {

    public PlayerStatus status() {
        return PlayerStatus.forId(statusId);
    }

    @Override
    public String getCommandName() {
        return CommandType.FRIEND_SOCIAL_STATUS_UPDATE.commandName;
    }
}
