package tech.zolhungaj.amqapi.client;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.zolhungaj.amqapi.client.exceptions.CommandBufferFullException;
import tech.zolhungaj.amqapi.client.exceptions.SocketDisconnectedException;
import tech.zolhungaj.amqapi.client.exceptions.UncheckedInterruptedException;

import java.io.Closeable;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SocketHandler implements Closeable {
    private static final Logger LOG = LoggerFactory.getLogger(SocketHandler.class);

    private static final String SOCKET_URL = "https://socket.animemusicquiz.com";


    private final BlockingQueue<JSONObject> commandQueue = new ArrayBlockingQueue<>(500);
    private Long currentPing = 0L;

    private final Socket socket;
    private int sessionId;

    public SocketHandler(String token, int port){
        IO.Options options = new IO.Options();
        options.reconnection = true;
        options.reconnectionAttempts = 5;
        options.reconnectionDelay = 1000;
        options.reconnectionDelayMax = 3000;
        options.query = "token=%s".formatted(token);
        this.socket = IO.socket(URI.create(SOCKET_URL +":%d".formatted(port)), options);
    }

    public void connect(){

        socket.on("sessionId", args -> {
            socketDebug("sessionId", args);
            Object sessionId = args[0];
            if(sessionId instanceof Integer i){
                this.sessionId = i;
            }
        });
        socket.on("command", args -> {
            socketDebug("command", args);
            if(args[0] instanceof JSONObject payload){
                //object with two entries: "command" and "data"
                addCommand(payload);
            }else{
                LOG.error("Malformed command, {} of type {}", args[0], args[0].getClass());
            }
        });
        socket.on(Socket.EVENT_CONNECT, args -> socketInfo("Connected!", args));
        socket.on(Socket.EVENT_CONNECTING, args -> socketInfo("Connecting...", args));
        socket.on(Socket.EVENT_CONNECT_TIMEOUT, args -> socketInfo("Connect timed out...", args));
        socket.on(Socket.EVENT_CONNECT_ERROR, args -> {
            socketInfo("Connection error", args);
            throw new SocketDisconnectedException("connect_error");
        });
        socket.on(Socket.EVENT_PING, args -> socketDebug("ping", args));
        socket.on(Socket.EVENT_PONG, args -> {
            if(args[0] instanceof Long l) {
                currentPing = l;
            }
            socketDebug("pong", args);
        }); //args[0] here is a Long

        socket.on(Socket.EVENT_MESSAGE, args -> socketDebug("message", args));

        socket.on(Socket.EVENT_DISCONNECT, args -> socketInfo("Socket disconnected...", args));
        socket.on(Socket.EVENT_RECONNECT_ERROR, args -> socketInfo("Reconnection error", args));
        socket.on(Socket.EVENT_RECONNECT, args -> socketInfo("Successfully reconnected! " + this.sessionId, args));
        socket.on(Socket.EVENT_RECONNECTING, args -> socketInfo("Reconnecting...", args));
        socket.on(Socket.EVENT_RECONNECT_FAILED, args -> {
            socketInfo("reconnect failed", args);
            throw new SocketDisconnectedException("Unable to reconnect to the server");
        });
        socket.on(Socket.EVENT_RECONNECT_ATTEMPT, args -> socketInfo("Attempting to reconnect: " + this.sessionId, args));
        socket.on(Socket.EVENT_ERROR, args -> {
            socketInfo("socket error", args);
            throw new SocketDisconnectedException("error");
        });

        socket.connect();
    }

    private void addCommand(JSONObject command){
        boolean success;
        try{
            success = commandQueue.offer(command, 1, TimeUnit.MINUTES);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            throw new UncheckedInterruptedException(e);
        }
        if(!success){
            throw new CommandBufferFullException();
        }
    }

    public JSONObject pollCommand(Duration timeout) throws InterruptedException{
        return commandQueue.poll(timeout.toNanos(), TimeUnit.NANOSECONDS);
    }

    public long getCurrentPing(){
        return currentPing;
    }

    private void socketDebug(String event, Object... args){
        if(LOG.isDebugEnabled()){
            LOG.debug(event);
            for(Object o : args){
                LOG.debug("    {}", o);
            }
        }else{
            socketInfo(event, args);//replace later
        }
    }

    private void socketInfo(String event, Object... args){
        if(LOG.isInfoEnabled()){
            LOG.info(event);
            for(Object o : args){
                LOG.info("    {}, {}", o, o.getClass());
            }
        }
    }

    @Override
    public void close(){
        socket.off();
        socket.disconnect();
        socket.close();
    }

}
