package tech.zolhungaj.amqapi.client;

public class TooManyRequestsException extends RuntimeException{
    public TooManyRequestsException(Throwable cause){
        super(cause);
    }
}
