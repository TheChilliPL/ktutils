package me.patrykanuszczyk.ktutils.random

import kotlin.random.Random

fun Random.nextChar(): Char {
    return this.nextBits(16).toChar()
}