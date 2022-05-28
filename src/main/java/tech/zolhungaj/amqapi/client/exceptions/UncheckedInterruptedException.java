package tech.zolhungaj.amqapi.client.exceptions;

public class UncheckedInterruptedException extends RuntimeException{
    public UncheckedInterruptedException(InterruptedException cause){
        super(cause);
    }
}
