package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;
import java.util.Optional;

public record PlayerAnswerResult(
        @Json(name = "positionSlot")
        int positionSlot,
        @Json(name = "score")
        int score,
        @Json(name = "correct")
        boolean correct,
        @Json(name = "pose")
        AvatarPose pose,
        @Json(name = "level")
        int level,
        @Json(name = "listStatus")
        Optional<ListStatus> listStatus,
        @Json(name = "showScore")
        Optional<Integer> showScore,
        @Json(name = "gamePlayerId")
        int gamePlayerId,
        @Json(name = "position")
        int position) {
        public PlayerAnswerResult{
                if(showScore == null) showScore = Optional.empty();
                if(showScore.isPresent() && showScore.get() == 0) showScore = Optional.empty();
                if(listStatus == null) listStatus = Optional.empty();
        }
}