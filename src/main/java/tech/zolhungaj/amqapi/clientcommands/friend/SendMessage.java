package tech.zolhungaj.amqapi.clientcommands.friend;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;

import java.util.Objects;

@Getter
@ToString
public final class SendMessage extends AbstractClientCommand {
    public SendMessage(){
        super(ClientCommandType.SEND_CHAT_MESSAGE);
    }
    private String msg;
    private Boolean teamMessage;

    @Builder
    public SendMessage(String message, Boolean isTeamMessage) {
        this();
        this.msg = message;
        this.teamMessage = Objects.requireNonNullElse(isTeamMessage, Boolean.FALSE);
    }
}
