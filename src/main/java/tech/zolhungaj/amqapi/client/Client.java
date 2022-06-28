package tech.zolhungaj.amqapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.zolhungaj.amqapi.client.handlers.SocketHandler;
import tech.zolhungaj.amqapi.client.handlers.WebHandler;
import tech.zolhungaj.amqapi.client.requests.Authentication;

import java.io.UncheckedIOException;
import java.time.Duration;

public class Client implements AutoCloseable{

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    private final WebHandler webHandler;
    private final SocketHandler socketHandler;


    public Client(String username, String password, boolean forceConnect){
        LOG.info("Starting Client...");
        this.webHandler = new WebHandler(new Authentication(username, password), forceConnect);
        webHandler.connect();
        this.socketHandler = new SocketHandler(webHandler.getToken(), webHandler.getPort());
        socketHandler.connect();
        LOG.info("Started Client!");
    }

    public void sendCommand(String command, Object data){
        try{
            var mapper = new ObjectMapper();
            String dataString = mapper.writeValueAsString(data);
            String completeCommand = """
                                        {"command":"%s","data":%s}\
                                        """.formatted(command, dataString);
            var jsonObject = new JSONObject(completeCommand);
            socketHandler.sendCommand(jsonObject);
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
