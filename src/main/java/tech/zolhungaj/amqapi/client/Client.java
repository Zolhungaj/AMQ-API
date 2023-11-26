package tech.zolhungaj.amqapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import tech.zolhungaj.amqapi.client.handlers.SocketHandler;
import tech.zolhungaj.amqapi.client.handlers.WebHandler;
import tech.zolhungaj.amqapi.client.requests.Authentication;

import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Slf4j
public class Client implements AutoCloseable{

    private final Authentication authentication;
    private WebHandler webHandler;
    private SocketHandler socketHandler;


    public Client(String username, String password){
        this.authentication = new Authentication(username, password);
    }

    public void start(boolean forceConnect){
        log.info("Starting Client...");
        this.webHandler = new WebHandler(this.authentication, forceConnect);
        this.webHandler.connect();
        this.socketHandler = new SocketHandler(webHandler.getToken(), webHandler.getPort());
        socketHandler.connect();
        log.info("Started Client!");
    }

    public void sendCommand(String group, String command, Object data){
        try{
            String dataString;
            if(data != null){
                var mapper = new ObjectMapper();
                dataString = """
                                ,"data":%s\
                                """.formatted(mapper.writeValueAsString(data));
            }else{
                dataString = "";
            }
            String completeCommand = """
                                        {"type":"%s","command":"%s"%s}\
                                        """.formatted(group, command, dataString);
            var jsonObject = new JSONObject(completeCommand);
            socketHandler.sendCommand(jsonObject);
            log.info("Sent command: {}", jsonObject);
        }catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    public ServerCommand pollCommand(Duration duration) throws InterruptedException, TimeoutException {
        JSONObject jsonObject = socketHandler.pollCommand(duration);
        String command = jsonObject.getString("command");
        if (!jsonObject.has("data")) {
            return new ServerCommand(
                    command,
                    new JSONObject()
            );
        }else if(jsonObject.optJSONObject("data") != null){
            return new ServerCommand(
                    command,
                    jsonObject.getJSONObject("data")
            );
        }else if(jsonObject.optJSONArray("data") != null){
            JSONObject wrapper = new JSONObject();
            wrapper.put("array", jsonObject.getJSONArray("data"));
            return new ServerCommand(
                    command,
                    wrapper
            );
        }else if(jsonObject.optString("data") != null){
            JSONObject wrapper = new JSONObject();
            wrapper.put("string", jsonObject.getString("data"));
            return new ServerCommand(
                    command,
                    wrapper
            );
        }else{
            log.error("Unknown command format: {}", jsonObject);
            return new ServerCommand(
                    command,
                    jsonObject //allows someone to hack their way around this if I haven't thought of a case
            );
        }
    }

    @Override
    public void close() {
        webHandler.close();
        socketHandler.close();
    }

    public record ServerCommand(
            String command,
            JSONObject data
    ) {
    }
}
