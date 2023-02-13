package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.ToString;
import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;
import tech.zolhungaj.amqapi.clientcommands.DirectDataCommand;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

@Getter
@ToString
public sealed class HostGame extends AbstractClientCommand implements DirectDataCommand permits HostSoloGame {
    @Json(name = "data")
    private final GameSettings settings;
    protected HostGame(ClientCommandType commandType, GameSettings settings) {
        super(commandType);
        this.settings = settings;
    }
    public HostGame(GameSettings settings) {
        this(ClientCommandType.HOST_GAME, settings.withGameMode(GameSettings.GameMode.MULTIPLAYER.value));
    }

    @Override
    public Object data() {
        return settings;
    }
}
