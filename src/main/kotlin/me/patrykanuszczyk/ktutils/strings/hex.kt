package me.patrykanuszczyk.ktutils.strings

fun ByteArray.toHex(): String =
    joinToString("") { it.toString(16) }

fun CharSequence.toHexBytes(): ByteArray =
    this.chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()

fun ByteArray.toLongBE(): Long {
    var value = 0L

    this.forEach { byte ->
        value = value shl 8
        value = value or (byte.toLong() and 0xFF)
    }

    return value
}