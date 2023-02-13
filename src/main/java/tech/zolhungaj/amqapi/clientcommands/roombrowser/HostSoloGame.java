package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

public final class HostSoloGame extends HostGame{
    public HostSoloGame(GameSettings settings){
        super(ClientCommandType.HOST_SOLO_GAME, settings.withGameMode(GameSettings.GameMode.SOLO.value).withRoomName("Solo"));
    }
}
