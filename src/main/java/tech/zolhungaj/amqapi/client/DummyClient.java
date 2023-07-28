package tech.zolhungaj.amqapi.client;

import lombok.extern.slf4j.Slf4j;
import tech.zolhungaj.amqapi.clientcommands.ClientCommand;

import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;

@Slf4j
public class DummyClient extends Client{
    public DummyClient() {
        super("username", "password");
    }

    Map<String, Map<String, Collection<Consumer<Object>>>> listeners = new HashMap<>();

    @Override
    public void start(boolean forceConnect){
        throw new UnsupportedOperationException("Dummy Client should not be started");
    }

    /**Allows listening for commands sent by the client
     * */
    public void registerCommandListener(ClientCommand command, Consumer<Object> consumer){
        this.registerCommandListener(command.type(), command.command(), consumer);
    }

    /**Allows listening for commands sent by the client
     * */
    public void registerCommandListener(String type, String command, Consumer<Object> consumer){
        var list = new ArrayList<>(List.of(consumer));
        if(listeners.containsKey(type)){
            Map<String, Collection<Consumer<Object>>> innerMap = listeners.get(type);
            if(innerMap.containsKey(command)){
                innerMap.get(command).addAll(list);
            }else{
                innerMap.put(command, list);
            }
        }else{
            listeners.put(type, new HashMap<>(Map.of(command, list)));
        }
    }

    @Override
    public void sendCommand(String type, String command, Object data) {
        Collection<Consumer<Object>> consumers = Optional.of(listeners)
                .map(map -> map.get(type))
                .map(map -> map.get(command))
                .orElseGet(List::of);
        consumers.forEach(consumer -> consumer.accept(data));
    }


    @Override
    public ServerCommand pollCommand(Duration duration) {
        throw new UnsupportedOperationException("Dummy Client should not be polled");
    }

    @Override
    public void close() {
        // empty just to override the super command
    }
}
