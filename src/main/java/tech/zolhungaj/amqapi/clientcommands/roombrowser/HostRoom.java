package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import tech.zolhungaj.amqapi.clientcommands.DirectDataCommand;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

public record HostRoom(
        GameSettings settings,
        boolean isSoloRoom
) implements RoomBrowserCommand, DirectDataCommand {
    public HostRoom{
        var settingsBuilder = settings.toBuilder();
        if (isSoloRoom) {
            settingsBuilder
                    .gameMode(GameSettings.GameMode.SOLO.value)
                    .roomName("Solo");
        } else {
            settingsBuilder.gameMode(GameSettings.GameMode.MULTIPLAYER.value);
        }
        settings = settingsBuilder.build();
    }
    public HostRoom(GameSettings settings){
        this(settings, false);
    }

    @Override
    public GameSettings data() {
        return settings;
    }

    @Override
    public String command() {
        if (isSoloRoom) {
            return "host solo room";
        } else {
            return "host room";
        }
    }
}
