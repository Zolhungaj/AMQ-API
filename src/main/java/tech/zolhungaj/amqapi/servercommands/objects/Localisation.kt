package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class Localisation(val key: String, val values: Map<String, String>?)
