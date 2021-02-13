package me.patrykanuszczyk.ktutils.math

infix fun Char.xor(other: Char): Char =
    (this.toInt() xor other.toInt()).toChar()

//TODO Other bitwise operations for Short, Byte, Char.