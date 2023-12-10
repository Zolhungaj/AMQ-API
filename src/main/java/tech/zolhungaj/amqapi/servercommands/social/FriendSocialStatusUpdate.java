package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerStatus;

import java.util.Optional;

public record FriendSocialStatusUpdate(
        String name,
        @Json(name = "socialStatus") PlayerStatus status,
        Optional<PlayerGameState> gameState
) implements Command {

    @Override
    public String commandName() {
        return CommandTypeOld.FRIEND_SOCIAL_STATUS_UPDATE.commandName;
    }
}
