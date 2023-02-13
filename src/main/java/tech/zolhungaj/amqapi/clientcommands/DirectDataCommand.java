package tech.zolhungaj.amqapi.clientcommands;

/**Workaround for when the data object is fed directly with a complex object
 * */
public interface DirectDataCommand {
    Object data();
}
