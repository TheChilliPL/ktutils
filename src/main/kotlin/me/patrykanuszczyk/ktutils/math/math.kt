package me.patrykanuszczyk.ktutils.math

import kotlin.math.pow
import kotlin.math.roundToLong

fun Double.round(decimals: Int): Double {
    val multiplier = 10.0.pow(decimals)

    return (this * multiplier).roundToLong() / multiplier
}

val niceFormatUnits = listOf("", "k", "M", "G", "T", "P", "E", "Z", "Y")
fun Number.niceFormat(
    precision: Int = 1,
    units: List<String> = niceFormatUnits,
    base: Int = 1000,
    separator: String = ""
): String {
    var num = toDouble()
    var unitId = 0
    while(num >= base && unitId < units.size - 1) {
        num /= base
        unitId++
    }
    num = num.round(precision)
    return "$num$separator${units[unitId]}"
}

val siBytesFormatUnits = niceFormatUnits.map { it + "B" }
fun Number.niceFormatAsSiBytes(
    precision: Int = 1,
    separator: String = " "
) = niceFormat(precision, separator = separator) + "B"

val binBytesFormatUnits = niceFormatUnits.map { it.toUpperCase() + "iB" }
fun Number.niceFormatAsBinBytes(
    precision: Int = 1,
    separator: String = " "
) = niceFormat (precision, separator = separator, base = 1024) + "iB"