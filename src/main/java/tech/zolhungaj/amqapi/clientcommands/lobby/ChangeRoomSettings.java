package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.DirectDataCommand;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

@DirectDataCommand("newSettings")
@CommandName("change game settings")
public record ChangeRoomSettings(
        GameSettings newSettings
) implements LobbyCommand {}
