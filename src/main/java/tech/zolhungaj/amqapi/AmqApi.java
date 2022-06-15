package tech.zolhungaj.amqapi;


import com.squareup.moshi.Moshi;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.zolhungaj.amqapi.client.Client;
import tech.zolhungaj.amqapi.commands.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AmqApi implements Runnable{
    private static final Logger LOG = LoggerFactory.getLogger(AmqApi.class);
    private final List<EventHandler> onList = new ArrayList<>();
    private final List<EventHandler> onceList = new ArrayList<>();

    private final String username;

    private final String password;

    private final boolean forceConnect;

    public AmqApi(String username, String password, boolean forceConnect) {
        this.username = username;
        this.password = password;
        this.forceConnect = forceConnect;
    }

    public void on(EventHandler event){
        this.onList.add(event);
    }
    public void once(EventHandler event){
        this.onceList.add(event);
    }

    private void handle(Command command){
        LOG.info("{}, {}, {}", command, command.getClass(), command.getName());

        onList
                .forEach(c -> c.call(command));
        onceList.removeIf(eventHandler -> eventHandler.call(command));
    }

    private Command serverCommandToCommand(Client.ServerCommand serverCommand) throws IOException {
        String commandName = serverCommand.command();
        JSONObject data = serverCommand.data();
        CommandType commandType = CommandType.forName(commandName);
        LOG.info("""
                ServerCommand: {}
                data: {}
                commandType: {}
                """, serverCommand, data, commandType);
        if(commandType == null){
            LOG.info("""
                    Unknown command:
                        command: {}
                        data: {}
                    """, commandName, data);
            return null;
        }
        var moshi = new Moshi
                .Builder()
                .build();
        String dataAsString = data.toString();
        return switch(commandType){
            case CHAT_MESSAGES -> moshi.adapter(GameChatUpdate.class).fromJson(dataAsString);
            case GAME_INVITE -> moshi.adapter(GameInvite.class).fromJson(dataAsString);
            case ONLINE_PLAYERS -> moshi.adapter(OnlinePlayerCountChange.class).fromJson(dataAsString);
        };
    }


    @Override
    public void run() {
        try(var client = new Client(username, password, forceConnect)){
            while(!Thread.interrupted()){
                Client.ServerCommand serverCommand = client.pollCommand(Duration.ofMinutes(1));
                Command command = serverCommandToCommand(serverCommand);
                if(command == null){
                    continue;
                }
                handle(command);
            }
        } catch (InterruptedException e) {
            LOG.error("AMQ-API Interrupted, ", e);
            Thread.currentThread().interrupt();
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
}
