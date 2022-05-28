package tech.zolhungaj.amqapi.client.exceptions;

public class CommandBufferFullException extends RuntimeException{
    public CommandBufferFullException(){
        super("Command buffer is full");
    }
}
