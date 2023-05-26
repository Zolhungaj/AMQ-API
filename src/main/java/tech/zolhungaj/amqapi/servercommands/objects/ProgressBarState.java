package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public class ProgressBarState{

    @Json(name = "length")
    private double totalLength;

    @Json(name = "played")
    private double currentPosition;
}