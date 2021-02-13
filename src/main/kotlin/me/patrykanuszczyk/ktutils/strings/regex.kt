package me.patrykanuszczyk.ktutils.strings

operator fun MatchResult.get(index: Int): String {
    return this.groupValues[index]
}