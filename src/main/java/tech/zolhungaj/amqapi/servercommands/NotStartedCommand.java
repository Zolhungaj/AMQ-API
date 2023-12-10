package tech.zolhungaj.amqapi.servercommands;

import org.json.JSONObject;

public record NotStartedCommand(String commandName, JSONObject data){}
