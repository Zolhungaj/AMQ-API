package tech.zolhungaj.amqapi.client.exceptions;

public class UnexpectedResponseException extends RuntimeException{
    public UnexpectedResponseException(Throwable cause){
        super(cause);
    }
}
