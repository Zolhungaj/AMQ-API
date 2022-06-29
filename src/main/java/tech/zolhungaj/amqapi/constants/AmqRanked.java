package tech.zolhungaj.amqapi.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class AmqRanked {
    public enum GAME_SERIES {
        CENTRAL(1, "Central", 'C', TimeZone.getTimeZone("Europe/Copenhagen")),
        WEST(2, "West", 'W', TimeZone.getTimeZone("America/Chicago")),
        EAST(3, "East", 'E', TimeZone.getTimeZone("Asia/Tokyo")),
        ;
        public final int id;
        public final String seriesName;
        public final Character symbol;
        public final TimeZone timeZone;

        private static final Map<Integer, GAME_SERIES> ID_MAP = new HashMap<>();
        static{
            for(GAME_SERIES gs : GAME_SERIES.values()){
                ID_MAP.put(gs.id, gs);
            }
        }

        GAME_SERIES(int id, String seriesName, Character symbol, TimeZone timeZone){
            this.id = id;
            this.seriesName = seriesName;
            this.symbol = symbol;
            this.timeZone = timeZone;
        }

        public static GAME_SERIES forId(int id){
            return ID_MAP.get(id);
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

    public enum RANKED_STATE {
        OFFLINE(0, "Offline"),
        LOBBY(1, "Lobby Up"),
        RUNNING(2, "Playing"),
        FINISHED(3, "Completed"),
        CHAMP_OFFLINE(4, "Championship - Offline"),
        CHAMP_LOBBY(5, "Championship - Lobby"),
        CHAMP_RUNNING(6, "Championship - Playing"),
        CHAMP_FINISHED(7, "Completed"),
        BREAK_DAY(8, "Ranked Rest Day")
        ;

        private static final Map<Integer, RANKED_STATE> ID_MAP = new HashMap<>();
        static{
            for(RANKED_STATE rs : RANKED_STATE.values()){
                ID_MAP.put(rs.id, rs);
            }
        }

        public final int id;
        public final String message;
        RANKED_STATE(int id, String message){
            this.id = id;
            this.message = message;
        }

        public static RANKED_STATE forId(int id){
            return ID_MAP.get(id);
        }
    }
}
