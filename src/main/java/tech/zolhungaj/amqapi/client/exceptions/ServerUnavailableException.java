package tech.zolhungaj.amqapi.client.exceptions;

public class ServerUnavailableException extends RuntimeException{
    public ServerUnavailableException(String cause){
        super(cause);
    }
}
