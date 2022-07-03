package tech.zolhungaj.amqapi.commands.objects;

import java.util.HashMap;
import java.util.Map;

public enum PlayerStatus {
    OFFLINE(0, "Offline"),
    ONLINE(1, "Online"),
    DO_NOT_DISTURB(2, "Do Not Disturb"),
    AWAY(3, "Away")
    ;
    private static final Map<Integer, PlayerStatus> ID_MAP = new HashMap<>();
    static{
        for(PlayerStatus gs : PlayerStatus.values()){
            ID_MAP.put(gs.id, gs);
        }
    }
    public final int id;
    public final String text;
    PlayerStatus(int id, String text){
        this.id = id;
        this.text = text;
    }


    /**
     * @return for valid values return corresponding PlayerStatus, otherwise return {@link #OFFLINE}
     */
    public static PlayerStatus forId(Integer id){
        return ID_MAP.getOrDefault(id, OFFLINE);
    }
}
