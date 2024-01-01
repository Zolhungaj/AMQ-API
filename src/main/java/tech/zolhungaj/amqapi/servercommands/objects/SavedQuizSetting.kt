package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class SavedQuizSetting(
    val id: Int,
    val name: String,
    val settingString: String
)
