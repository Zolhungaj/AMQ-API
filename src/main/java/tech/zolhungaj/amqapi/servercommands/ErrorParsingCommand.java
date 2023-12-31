package tech.zolhungaj.amqapi.servercommands;

import org.json.JSONObject;

public record ErrorParsingCommand(String commandName, JSONObject data, Throwable error){}
