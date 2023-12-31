package tech.zolhungaj.amqapi.servercommands.expandlibrary

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.expand.ExpandLibraryEntry

@JvmRecord
@CommandType("expandLibrary questions")
data class ExpandLibraryEntryList(
    val success : Boolean,
    val issue : String?,
    @Json(name = "questions")
    val entries : List<ExpandLibraryEntry>
)
