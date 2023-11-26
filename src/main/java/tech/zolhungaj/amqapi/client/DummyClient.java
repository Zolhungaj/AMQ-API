package tech.zolhungaj.amqapi.client;

import lombok.extern.slf4j.Slf4j;

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
    public void registerCommandListener(String group, String commandName, Consumer<Object> consumer){
        var list = new ArrayList<>(List.of(consumer));
        if(listeners.containsKey(group)){
            Map<String, Collection<Consumer<Object>>> innerMap = listeners.get(group);
            if(innerMap.containsKey(commandName)){
                innerMap.get(commandName).addAll(list);
            }else{
                innerMap.put(commandName, list);
            }
        }else{
            listeners.put(group, new HashMap<>(Map.of(commandName, list)));
        }
    }

    @Override
    public void sendCommand(String group, String command, Object data) {
        Collection<Consumer<Object>> consumers = Optional.of(listeners)
                .map(map -> map.get(group))
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
