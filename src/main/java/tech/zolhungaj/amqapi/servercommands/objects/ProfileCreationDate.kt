package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json
import java.time.LocalDate

@JvmRecord
data class ProfileCreationDate(
    @Json(name = "hidden") val isHidden: Boolean,
    @Json(name = "adminView") val isAdminView: Boolean,
    @Json(name = "value") val value: LocalDate?
)
