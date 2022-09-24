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

    public void sendCommand(String type, String command, Object data){
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
                                        """.formatted(type, command, dataString);
            var jsonObject = new JSONObject(completeCommand);
            socketHandler.sendCommand(jsonObject);
            log.info("Sent command: {}", jsonObject);
        }catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    public long getCurrentPing(){
        return socketHandler.getCurrentPing();
    }

    public ServerCommand pollCommand(Duration duration) throws InterruptedException{
        JSONObject jsonObject = socketHandler.pollCommand(duration);
        return new ServerCommand(
            jsonObject.getString("command"),
            jsonObject.getJSONObject("data")
        );
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
