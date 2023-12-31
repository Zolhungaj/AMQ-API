package tech.zolhungaj.amqapi.servercommands.objects.expand

@JvmRecord
data class ExpandLibrarySongVersionEntryClosed(
    val resolution: Int?,
    val status: ExpandSongStatus
)
