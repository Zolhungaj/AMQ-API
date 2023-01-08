package tech.zolhungaj.amqapi.client.exceptions;

public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String cause){
        super(cause);
    }
    public AuthenticationFailedException(Throwable cause){
        super(cause);
    }
}
