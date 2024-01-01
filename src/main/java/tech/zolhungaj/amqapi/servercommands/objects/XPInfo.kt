package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class XPInfo(
    val xpPercent: Double,  //literally just xpIntoLevel / xpForLevel
    val lastGain: Int,
    val xpForLevel: Int,
    val xpIntoLevel: Int
) {
    fun xpForNextLevel(): Int {
        return xpForLevel - xpIntoLevel
    }
}
