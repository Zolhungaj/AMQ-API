package tech.zolhungaj.amqapi.clientcommands;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ClientCommand{
    @JsonIgnore
    String type();
    @JsonIgnore
    String command();
}
