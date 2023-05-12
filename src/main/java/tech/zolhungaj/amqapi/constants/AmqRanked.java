package tech.zolhungaj.amqapi.constants;

import com.squareup.moshi.Json;

import java.util.TimeZone;

public class AmqRanked {
    public enum RankedSeries {
        @Json(name = "1")
        CENTRAL("Central", 'C', TimeZone.getTimeZone("Europe/Copenhagen")),
        @Json(name = "2")
        WEST("West", 'W', TimeZone.getTimeZone("America/Chicago")),
        @Json(name = "3")
        EAST("East", 'E', TimeZone.getTimeZone("Asia/Tokyo")),
        ;
        public final String seriesName;
        public final Character symbol;
        public final TimeZone timeZone;

        RankedSeries(String seriesName, Character symbol, TimeZone timeZone){
            this.seriesName = seriesName;
            this.symbol = symbol;
            this.timeZone = timeZone;
        }
    }

    public enum RANKED_TIME {
        HOUR(20),
        MINUTE(30),
        SECOND(0);

        public final int value;
        RANKED_TIME(int value){
            this.value = value;
        }
    }

    public enum RankedState {
        @Json(name = "0")
        OFFLINE("Offline"),
        @Json(name = "1")
        LOBBY("Lobby Up"),
        @Json(name = "2")
        RUNNING("Playing"),
        @Json(name = "3")
        FINISHED("Completed"),
        @Json(name = "4")
        CHAMP_OFFLINE("Championship - Offline"),
        @Json(name = "5")
        CHAMP_LOBBY("Championship - Lobby"),
        @Json(name = "6")
        CHAMP_RUNNING("Championship - Playing"),
        @Json(name = "7")
        CHAMP_FINISHED("Completed"),
        @Json(name = "8")
        BREAK_DAY("Ranked Rest Day")
        ;
        public final String message;
        RankedState(String message){
            this.message = message;
        }
    }
}
