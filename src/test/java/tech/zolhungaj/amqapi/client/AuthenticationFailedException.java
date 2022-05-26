package tech.zolhungaj.amqapi.client;

public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String cause){
        super(cause);
    }
}
