package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class NexusBuff(
    @Json(name = "fileName") val filename: String,

    @Json(name = "debuff") val isDebuff: Boolean,

    @Json(name = "name") val buffName: String,

    val description: String
) 