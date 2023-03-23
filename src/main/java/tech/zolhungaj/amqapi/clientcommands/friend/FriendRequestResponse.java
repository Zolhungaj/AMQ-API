package tech.zolhungaj.amqapi.clientcommands.friend;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;

@Getter
@Setter
@ToString
public final class FriendRequestResponse extends AbstractClientCommand {
    public FriendRequestResponse(){
        super(ClientCommandType.RESPOND_TO_FRIEND_REQUEST);
    }
    private String target;
    private Boolean accept;

    @Builder
    public FriendRequestResponse(String target, Boolean accept) {
        this();
        this.target = target;
        this.accept = accept;
    }
}
