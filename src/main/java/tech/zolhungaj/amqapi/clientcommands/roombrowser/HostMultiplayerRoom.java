package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

@CommandName("host room")
public record HostMultiplayerRoom(
        GameSettings settings,
        Boolean communityMode
) implements RoomBrowserCommand {
    public HostMultiplayerRoom {
        var settingsBuilder = settings.toBuilder();
        settingsBuilder.gameMode(GameSettings.GameMode.MULTIPLAYER.value);
        settings = settingsBuilder.build();
    }
    public HostMultiplayerRoom(GameSettings settings) {
        this(settings, false);
    }
}
