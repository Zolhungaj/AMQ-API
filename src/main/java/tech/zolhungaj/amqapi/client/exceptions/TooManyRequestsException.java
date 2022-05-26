package tech.zolhungaj.amqapi.client.exceptions;

public class TooManyRequestsException extends RuntimeException{
    public TooManyRequestsException(Throwable cause){
        super(cause);
    }
}
