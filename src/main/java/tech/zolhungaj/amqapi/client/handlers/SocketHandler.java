package tech.zolhungaj.amqapi.client.handlers;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.parser.Packet;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import tech.zolhungaj.amqapi.client.exceptions.CommandBufferFullException;
import tech.zolhungaj.amqapi.client.exceptions.SocketDisconnectedException;
import tech.zolhungaj.amqapi.client.exceptions.UncheckedInterruptedException;

import java.io.Closeable;
import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class SocketHandler implements Closeable {
    private static final String EVENT_COMMAND = "command";

    private static final String SOCKET_URL = "https://socket.animemusicquiz.com";


    private final BlockingQueue<JSONObject> commandQueue = new ArrayBlockingQueue<>(500);

    private final Socket socket;
    private int sessionId;

    public SocketHandler(String token, int port){
        IO.Options options = new IO.Options();
        options.reconnection = true;
        options.reconnectionAttempts = 5;
        options.reconnectionDelay = 1000;
        options.reconnectionDelayMax = 3000;
        options.query = "token=%s".formatted(token);
        options.auth = Collections.singletonMap("token", token);
        this.socket = IO.socket(URI.create(SOCKET_URL +":%d".formatted(port)), options);
        log.info("Created socket {}", socket);
    }

    public void connect(){
        socket.onAnyIncoming(args -> socketInfo("Incoming event:", args));

        socket.on("sessionId", args -> {
            socketDebug("sessionId", args);
            Object sessionId = args[0];
            if(sessionId instanceof Integer i){
                this.sessionId = i;
                log.info("Session id: {}", sessionId);
            }
        });
        socket.on(EVENT_COMMAND, args -> {
            socketDebug(EVENT_COMMAND, args);
            if(args[0] instanceof JSONObject payload){
                //object with two entries: "command" and "data"
                addCommand(payload);
            }else{
                log.error("Malformed command, {} of type {}", args[0], args[0].getClass());
            }
        });
        socket.on(Socket.EVENT_CONNECT, args -> socketInfo("Connected!", args));
        socket.on(Socket.EVENT_CONNECT_ERROR, args -> {
            socketInfo("Connection error", args);
            throw new SocketDisconnectedException("connect_error");
        });
        socket.on(Socket.EVENT_DISCONNECT, args -> socketInfo("Socket disconnected...", args));
        Manager manager = socket.io();

        manager.on(Manager.EVENT_ERROR, args -> {
            socketInfo("socket error", args);
            throw new SocketDisconnectedException("error");
        });
        manager.on(Manager.EVENT_RECONNECT, args -> socketInfo("Successfully reconnected! " + this.sessionId, args));
        manager.on(Manager.EVENT_RECONNECT_ATTEMPT, args -> socketInfo("Attempting to reconnect: " + this.sessionId, args));
        manager.on(Manager.EVENT_RECONNECT_ERROR, args -> socketInfo("Reconnection error", args));
        manager.on(Manager.EVENT_RECONNECT_FAILED, args -> {
            socketInfo("reconnect failed", args);
            throw new SocketDisconnectedException("Unable to reconnect to the server");
        });
        manager.on(Manager.EVENT_TRANSPORT, args -> socketDebug("transport", args));
        manager.on(Manager.EVENT_CLOSE, args -> socketInfo("close", args));
        manager.on(Manager.EVENT_OPEN, args -> socketInfo("open", args));
        manager.on(Manager.EVENT_PACKET, args -> socketDebug("packet", args));

        socket.connect();
    }

    private void addCommand(JSONObject command){
        boolean success;
        try{
            success = commandQueue.offer(command, 1, TimeUnit.MINUTES);
            log.debug("Added command to queue: {}", command);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            throw new UncheckedInterruptedException(e);
        }
        if(!success){
            throw new CommandBufferFullException();
        }
    }

    public JSONObject pollCommand(Duration timeout) throws InterruptedException, TimeoutException {
        JSONObject value = commandQueue.poll(timeout.toNanos(), TimeUnit.NANOSECONDS);
        if(value == null){
            throw new TimeoutException();
        }
        return value;
    }

    public void sendCommand(JSONObject payload){
        socket.emit(EVENT_COMMAND, payload);
        log.debug("Sent command: {}", payload);
    }

    private void socketDebug(String event, Object... args){
        if(log.isDebugEnabled()){
            log.debug(event);
            for(Object o : args){
                if(o instanceof Packet<?> packet){
                    log.debug("""
                                 Packet:
                                     type: {}
                                     id: {}
                                     nsp: {}
                                     data: {}
                                     attachments: {}
                             """, packet.type, packet.id, packet.nsp, packet.data, packet.attachments);
                }else{
                    log.debug("    {}, {}", o, o.getClass());
                }
            }
        }else{
            socketInfo(event, args);//replace later
        }
    }

    private void socketInfo(String event, Object... args){
        if(log.isInfoEnabled()){
            log.info(event);
            for(Object o : args){
                log.info("    {}, {}", o, o.getClass());
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
