package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.ClientCommand;

public interface SocialCommand extends ClientCommand {
    @Override
    default String type() {
        return "social";
    }
}
