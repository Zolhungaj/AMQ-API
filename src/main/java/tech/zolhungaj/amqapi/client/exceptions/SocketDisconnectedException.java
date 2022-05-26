package tech.zolhungaj.amqapi.client.exceptions;

public class SocketDisconnectedException extends RuntimeException {
    public SocketDisconnectedException(String cause){
        super(cause);
    }
}
