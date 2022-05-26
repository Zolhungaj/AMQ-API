package tech.zolhungaj.amqapi.client;

public class ServerUnavailableException extends RuntimeException{
    public ServerUnavailableException(Throwable cause){
        super(cause);
    }
}
