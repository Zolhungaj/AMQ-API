package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

@CommandName("host solo room")
public record HostSoloRoom(
        GameSettings settings,
        Boolean communityMode
) implements RoomBrowserCommand {
    public HostSoloRoom {
        var settingsBuilder = settings.toBuilder();
        settingsBuilder
                .gameMode(GameSettings.GameMode.SOLO.value)
                .roomName("Solo");
        settings = settingsBuilder.build();
    }
    public HostSoloRoom(GameSettings settings) {
        this(settings, false);
    }
}
