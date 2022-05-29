package tech.zolhungaj.amqapi.client;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.zolhungaj.amqapi.client.exceptions.*;
import tech.zolhungaj.amqapi.client.requests.Authentication;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

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





    @Override
    public void close() {
        webHandler.close();
        socketHandler.close();
    }
}
