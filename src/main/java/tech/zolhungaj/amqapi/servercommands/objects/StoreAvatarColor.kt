package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json
import java.util.*

@JvmRecord
data class StoreAvatarColor(
    val editor: String?,
    val limited: Boolean,
    val colorId: Int,
    val active: Boolean,
    @Json(name = "backgroundVert") val backgroundVertical: String,
    val defaultColor: Boolean,
    val tierId: Int?,
    val price: Int,
    val eventColor: Boolean,  //Always 0?
    val unique: Boolean,
    val name: String,
    val exclusive: Boolean,
    val sizeModifier: Int
)
