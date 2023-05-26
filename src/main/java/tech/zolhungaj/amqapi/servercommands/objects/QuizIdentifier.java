package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.time.OffsetDateTime;

public class QuizIdentifier {

	@Json(name = "quizId")
	private String quizUuid;

	@Json(name = "startTime")
	private OffsetDateTime startTime;

	@Json(name = "roomName")
	private String roomName;
}