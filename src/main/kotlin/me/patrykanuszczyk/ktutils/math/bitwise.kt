package me.patrykanuszczyk.ktutils.math

infix fun Char.xor(other: Char): Char =
    (this.code xor other.code).toChar()

//TODO Other bitwise operations for Short, Byte, Char.