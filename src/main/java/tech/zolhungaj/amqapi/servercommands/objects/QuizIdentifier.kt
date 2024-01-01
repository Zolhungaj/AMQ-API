package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json
import java.time.OffsetDateTime

@JvmRecord
data class QuizIdentifier(
    @Json(name = "quizId") val quizUuid: String,
    @Json(name = "startTime") val startTime: OffsetDateTime,
    @Json(name = "roomName") val roomName: String
) 