package me.patrykanuszczyk.ktutils.math

import kotlin.math.pow
import kotlin.math.roundToLong

fun Double.round(decimals: Int): Double {
    val multiplier = 10.0.pow(decimals)

    return (this * multiplier).roundToLong() / multiplier
}

val niceFormatUnits = listOf("", "k", "M")
fun Number.niceFormat(
    precision: Int = 1,
    units: List<String> = niceFormatUnits,
    base: Int = 1000
): String {
    var num = toDouble()
    var unitId = 0
    while(num > base && unitId < units.size - 1) {
        num /= base
        unitId++
    }
    num = num.round(precision)
    return "$num${units[unitId]}"
}