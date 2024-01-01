package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.time.OffsetDateTime;

public record QuizIdentifier(
		@Json(name = "quizId")
		String quizUuid,
		@Json(name = "startTime")
		OffsetDateTime startTime,
		@Json(name = "roomName")
		String roomName
) {

}