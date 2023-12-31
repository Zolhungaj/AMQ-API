package tech.zolhungaj.amqapi.servercommands;

import org.json.JSONObject;

public record UnregisteredCommand(String commandName, JSONObject data){}
