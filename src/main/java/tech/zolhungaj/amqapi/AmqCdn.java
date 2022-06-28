package tech.zolhungaj.amqapi;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class AmqCdn {

    public static final URI CDN_URL = URI.create("https://cdn.animemusicquiz.com");
    public static final URI AVATAR_URL = CDN_URL.resolve("/v1/avatars");
	public static final URI BADGE_URL = CDN_URL.resolve("/v1/badges");
	public static final URI EMOTE_URL = CDN_URL.resolve("/v1/emotes");
	public static final URI STORE_ICON_URL = CDN_URL.resolve("/v1/store-categories");
	public static final URI BACKGROUND_URL = CDN_URL.resolve("/v1/backgrounds");
	public static final URI TICKET_URL = CDN_URL.resolve("/v1/ui/currency");

    public enum AVATAR_SIZE {
        SMALLEST(100),
        SMALLER(150),
        SMALL(250),
        MEDIUM(350),
        LARGE(500),
        LARGER(600),
        LARGEST(900);

        public final int size;
        AVATAR_SIZE(int size){
            this.size = size;
        }
    }
    public enum AVATAR_POSE {
        BASIC(1, "Basic.webp"),
        THINKING(2, "Thinking.webp"),
        WAITING(3, "Waiting.webp"),
        WRONG(4, "Wrong.webp"),
        RIGHT(5, "Right.webp"),
        CONFUSED(6, "Confused.webp");
        public final int id;
        public final String filename;
        AVATAR_POSE(int id, String filename){
            this.id = id;
            this.filename = filename;
        }
    }
    public enum AVATAR_HEAD_SIZE {
        SMALL(100),
        MEDIUM(150),
        LARGE(250);
        public final int size; //width
        AVATAR_HEAD_SIZE(int size){
            this.size = size;
        }
    }

    public static final String AVATAR_HEAD_FILENAME = "Head.webp";

    public enum BADGE_SIZE {
        SMALLEST(30),
        SMALLER(50),
        SMALL(100),
        LARGE(200),
        LARGER(300),
        LARGEST(450);
        public final int size; //width
        BADGE_SIZE(int size){
            this.size = size;
        }
    }

    public static final String PATREON_PREVIEW_BADGE_FILENAME = "patreon-36.webp";

    public enum STORE_ICON_SIZE {
        SMALL(130, 172),
        MEDIUM(150, 198),
        LARGE(200, 264);
        public final int height;
        public final int width;
        STORE_ICON_SIZE(int height, int width){
            this.height = height;
            this.width = width;
        }
    }
    public enum BACKGROUND_SIZE {
        SMALL(200),
        MEDIUM(250),
        LARGE(400);
        public final int size;
        BACKGROUND_SIZE(int size){
            this.size = size;
        }
    }
    public static final int BACKGROUND_STORE_TILE_SIZE = BACKGROUND_SIZE.LARGE.size;
    public static final int BACKGROUND_STORE_PREVIEW_SIZE = BACKGROUND_SIZE.MEDIUM.size;
    public static final int BACKGROUND_ROOM_BROWSER_SIZE = BACKGROUND_SIZE.MEDIUM.size;
    public static final int BACKGROUND_GAME_SIZE = BACKGROUND_SIZE.SMALL.size;

    public enum EMOTE_SIZE {
        SMALLEST(30),
        SMALL(50),
        MEDIUM(100),
        LARGE(150);
        public final int size;
        EMOTE_SIZE(int size){
            this.size = size;
        }
    }

    public enum TICKET_SIZE {
        SMALLEST(30),
        SMALL(50),
        MEDIUM(100),
        LARGE(200),
        LARGEST(250);
        public final int size;
        TICKET_SIZE(int size){
            this.size = size;
        }
    }

    public enum TICKET_FILENAME {
        SIMPLE(1, "ticket1.webp"),
        ADVANCED(2, "ticket2.webp"),
        MAJOR(3, "ticket3.webp"),
        EXCLUSIVE(4, "ticket4.webp");

        private static final Map<Integer, TICKET_FILENAME> forNameMap = new HashMap<>();
        static{
            for(TICKET_FILENAME tf : TICKET_FILENAME.values()){
                forNameMap.put(tf.tierId, tf);
            }
        }
        public final int tierId;
        public final String filename;

        TICKET_FILENAME(int tierId, String filename){
            this.tierId = tierId;
            this.filename = filename;
        }
        public TICKET_FILENAME forName(int tierId){
            return forNameMap.get(tierId);
        }
    }

	public static final URI RHYTHM_ICON_PATH = TICKET_URL.resolve("30px/rhythm.webp");

    public static String getSizePath(int size){
        return "%dpx".formatted(size);
    }

    public static URI createAvatarUrl(String avatar,
                                      String outfit,
                                      String option,
                                      boolean optionOn,
                                      String color,
                                      AVATAR_SIZE size,
                                      AVATAR_POSE pose){
        if(!optionOn){
            option = "No " + option;
        }
        return AVATAR_URL
                .resolve(avatar)
                .resolve(outfit)
                .resolve(option)
                .resolve(color)
                .resolve(getSizePath(size.size))
                .resolve(pose.filename);
    }

    public static URI createAvatarHeadUrl(String avatar,
                                          String outfit,
                                          String option,
                                          boolean optionOn,
                                          String color,
                                          AVATAR_HEAD_SIZE size){
        if(!optionOn){
            option = "No " + option;
        }
        return AVATAR_URL
                .resolve(avatar)
                .resolve(outfit)
                .resolve(option)
                .resolve(color)
                .resolve(getSizePath(size.size))
                .resolve(AVATAR_HEAD_FILENAME);
    }

    public static URI createAvatarBackground(String filename, BACKGROUND_SIZE size){
        boolean isSvg = Pattern
                .compile("svg")
                .matcher(filename)
                .find();
        if(isSvg){
            return BACKGROUND_URL
                    .resolve("svg")
                    .resolve(filename);
        }else{
            return BACKGROUND_URL
                    .resolve(getSizePath(size.size))
                    .resolve(filename);
        }
    }
}
