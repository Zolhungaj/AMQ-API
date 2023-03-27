package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerStatus;

import java.util.Optional;

public record FriendSocialStatusUpdate(
        String name,
        @Json(name = "socialStatus") int statusId,
        Optional<PlayerGameState> gameState
) implements Command {

    public PlayerStatus status() {
        return PlayerStatus.forId(statusId);
    }

    @Override
    public String commandName() {
        return CommandType.FRIEND_SOCIAL_STATUS_UPDATE.commandName;
    }
}
