package tech.zolhungaj.amqapi.constants;

import tech.zolhungaj.amqapi.servercommands.objects.AvatarPose;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;
import tech.zolhungaj.amqapi.servercommands.objects.StoreAvatar;
import tech.zolhungaj.amqapi.servercommands.objects.Emote;

import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class AmqCdn {

    public static final URI CDN_URL = URI.create("https://cdn.animemusicquiz.com");
    public static final URI AVATAR_URL = CDN_URL.resolve("/v1/avatars");
	public static final URI BADGE_URL = CDN_URL.resolve("/v1/badges");
	public static final URI EMOTE_URL = CDN_URL.resolve("/v1/emotes");
	public static final URI STORE_ICON_URL = CDN_URL.resolve("/v1/store-categories");
	public static final URI BACKGROUND_URL = CDN_URL.resolve("/v1/backgrounds");
	public static final URI TICKET_URL = CDN_URL.resolve("/v1/ui/currency");

    public static final String IMAGE_FILE_EXTENSION = ".webp";

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

    public enum AVATAR_HEAD_SIZE {
        SMALL(100),
        MEDIUM(150),
        LARGE(250);
        public final int size; //width
        AVATAR_HEAD_SIZE(int size){
            this.size = size;
        }
    }

    public static final String AVATAR_HEAD_FILENAME = "Head" + IMAGE_FILE_EXTENSION;

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

    public static final String PATREON_PREVIEW_BADGE_FILENAME = "patreon-36" + IMAGE_FILE_EXTENSION;

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

    public enum BackgroundOrientation {
        LANDSCAPE,
        PORTRAIT
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
        SIMPLE(1, "ticket1" + IMAGE_FILE_EXTENSION),
        ADVANCED(2, "ticket2" + IMAGE_FILE_EXTENSION),
        MAJOR(3, "ticket3" + IMAGE_FILE_EXTENSION),
        EXCLUSIVE(4, "ticket4" + IMAGE_FILE_EXTENSION);

        private static final Map<Integer, TICKET_FILENAME> ID_MAP = new HashMap<>();
        static{
            for(TICKET_FILENAME tf : TICKET_FILENAME.values()){
                ID_MAP.put(tf.tierId, tf);
            }
        }
        public final int tierId;
        public final String filename;

        TICKET_FILENAME(int tierId, String filename){
            this.tierId = tierId;
            this.filename = filename;
        }
        public static TICKET_FILENAME forId(int tierId){
            return ID_MAP.get(tierId);
        }
    }

	public static final URI RHYTHM_ICON_PATH = TICKET_URL.resolve("30px").resolve("rhythm" + IMAGE_FILE_EXTENSION);

    public static URI createAvatarUrl(PlayerAvatar avatar, boolean optionOn, AVATAR_SIZE size, AvatarPose pose){
        return createAvatarUrl(
                avatar.avatar().avatarName(),
                avatar.avatar().outfitName(),
                avatar.avatar().optionName(),
                avatar.avatar().colorName(),
                optionOn,
                size,
                pose);
    }

    public static URI createAvatarUrl(StoreAvatar avatar, boolean optionOn, AVATAR_SIZE size, AvatarPose pose){
        return createAvatarUrl(
                avatar.avatarName(),
                avatar.outfitName(),
                avatar.optionName(),
                avatar.colorName(),
                optionOn,
                size,
                pose);
    }
    public static URI createAvatarUrl(String avatar,
                                      String outfit,
                                      String option,
                                      String color,
                                      boolean optionOn,
                                      AVATAR_SIZE size,
                                      AvatarPose pose){
        if(!optionOn){
            option = "No " + option;
        }
        return AVATAR_URL
                .resolve(avatar)
                .resolve(outfit)
                .resolve(option)
                .resolve(color)
                .resolve(getSizePath(size.size))
                .resolve(resolveAvatarPoseFilename(pose));
    }

    public static String resolveAvatarPoseFilename(AvatarPose pose){
        return switch(pose){
            case BASIC -> "Basic";
            case THINKING -> "Thinking";
            case WAITING -> "Waiting";
            case WRONG -> "Wrong";
            case RIGHT -> "Right";
            case CONFUSED -> "Confused";
        } + IMAGE_FILE_EXTENSION;
    }

    public static URI createAvatarHeadUrl(PlayerAvatar avatar, boolean optionOn, AVATAR_HEAD_SIZE size){
        return createAvatarHeadUrl(
                avatar.avatar().avatarName(),
                avatar.avatar().outfitName(),
                avatar.avatar().optionName(),
                avatar.avatar().colorName(),
                optionOn,
                size
        );
    }

    public static URI createAvatarHeadUrl(StoreAvatar avatar, boolean optionOn, AVATAR_HEAD_SIZE size){
        return createAvatarHeadUrl(
                avatar.avatarName(),
                avatar.outfitName(),
                avatar.optionName(),
                avatar.colorName(),
                optionOn,
                size
        );
    }

    public static URI createAvatarHeadUrl(String avatar,
                                          String outfit,
                                          String option,
                                          String color,
                                          boolean optionOn,
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


    public static URI createAvatarBackgroundUrl(PlayerAvatar avatar, BACKGROUND_SIZE size, BackgroundOrientation orientation){
        String background = switch (orientation){
            case LANDSCAPE -> avatar.background().backgroundHorizontal();
            case PORTRAIT -> avatar.background().backgroundVertical();
        };
        return createAvatarBackgroundUrl(background, size);
    }

    public static URI createAvatarBackgroundUrl(StoreAvatar avatar, BACKGROUND_SIZE size){
        return createAvatarBackgroundUrl(avatar.backgroundVertical, size);
    }

    public static URI createAvatarBackgroundUrl(String filename, BACKGROUND_SIZE size){
        if(filename.contains("svg")){
            return BACKGROUND_URL
                    .resolve("svg")
                    .resolve(filename);
        }else{
            return BACKGROUND_URL
                    .resolve(getSizePath(size.size))
                    .resolve(filename);
        }
    }

    public static URI createBadgeUrl(StoreAvatar avatar, BADGE_SIZE size){
        return createBadgeUrl(avatar.badgeFileName(), size);
    }

    public static URI createBadgeUrl(String filename, BADGE_SIZE size){
        return BADGE_URL
                .resolve(getSizePath(size.size))
                .resolve(filename);
    }

    public static URI createStoreIconUrl(String iconName, STORE_ICON_SIZE size){
        String iconFilename = iconName
                .replaceAll("\\s", "-")
                .concat(IMAGE_FILE_EXTENSION);
        return STORE_ICON_URL
                .resolve(getSizePath(size.height))
                .resolve(iconFilename);
    }

    public static URI createStoreAvatarUrl(StoreAvatar avatar, STORE_ICON_SIZE size){
        return createStoreAvatarUrl(avatar.avatarName(), avatar.outfitName(), size);
    }

    public static URI createStoreAvatarUrl(String avatar, String outfit, STORE_ICON_SIZE size){
        String filename = "%s_%s".formatted(avatar, formatStoreIconOutfit(outfit));
        return createStoreIconUrl(filename, size);
    }

    private static String formatStoreIconOutfit(String outfit){
        return outfit
                .toLowerCase(Locale.ROOT)
                .replaceAll("[ -]", "_");
    }

    public static URI createEmoteUrl(Emote emote, EMOTE_SIZE size){
        return createEmoteUrl(emote.emoteName(), size);
    }

    public static URI createEmoteUrl(String emoteName, EMOTE_SIZE size){
        String emoteFilename = emoteName + IMAGE_FILE_EXTENSION;
        return EMOTE_URL
                .resolve(getSizePath(size.size))
                .resolve(emoteFilename);
    }

    public static URI createTicketUrl(int ticketTierId, TICKET_SIZE size){
        String ticketFilename = TICKET_FILENAME
                .forId(ticketTierId)
                .filename;

        return TICKET_URL
                .resolve(getSizePath(size.size))
                .resolve(ticketFilename);
    }

    public static String getSizePath(int size){
        return "%dpx".formatted(size);
    }
}
