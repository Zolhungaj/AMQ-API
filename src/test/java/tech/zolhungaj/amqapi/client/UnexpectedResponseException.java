package tech.zolhungaj.amqapi.client;

public class UnexpectedResponseException extends RuntimeException{
    public UnexpectedResponseException(Throwable cause){
        super(cause);
    }
}
