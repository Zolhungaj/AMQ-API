package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public enum PlayerStatus {
    @Json(name = "0")
    OFFLINE("Offline"),
    @Json(name = "1")
    ONLINE("Online"),
    @Json(name = "2")
    DO_NOT_DISTURB("Do Not Disturb"),
    @Json(name = "3")
    AWAY("Away")
    ;
    public final String text;
    PlayerStatus(String text){
        this.text = text;
    }
}
